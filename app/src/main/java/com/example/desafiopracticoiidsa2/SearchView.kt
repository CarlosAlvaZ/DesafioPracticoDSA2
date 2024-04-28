package com.example.desafiopracticoiidsa2

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiopracticoiidsa2.Interceptor.HeaderInterceptor
import com.example.desafiopracticoiidsa2.Models.Track
import com.example.desafiopracticoiidsa2.adapters.TrackAdapter
import com.example.desafiopracticoiidsa2.databinding.FragmentSearchViewBinding
import com.example.desafiopracticoiidsa2.services.SpotifyApiService
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder

class SearchView : Fragment(), SearchView.OnQueryTextListener {

    val args: SearchViewArgs by navArgs()

    private var _binding: FragmentSearchViewBinding? = null
    private val binding get() = _binding!!

    private val searchResults = mutableListOf<Track>()
    private lateinit var trackAdapter: TrackAdapter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type = args.searchType
        binding.subtitle.text = "Searching For ${type}"
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Globals.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(getClient()).build()
    }

    private fun getClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(HeaderInterceptor(getToken())).build()

    private fun getToken(): String {
        val sharedPreferences =
            activity?.getSharedPreferences(Globals.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        return sharedPreferences?.getString(Globals.TOKEN, "")!!
    }

    private fun searchElement(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(SpotifyApiService::class.java)
                .searchByTrack(URLEncoder.encode(query, "UTF-8"), "track")
            println("REQUEST: ${call.toString()}")
            val res = call.body()
            activity?.runOnUiThread {
                if (call.isSuccessful) {
                    val response = res?.items ?: emptyList()
                    searchResults.clear()
                    searchResults.addAll(response)
                    trackAdapter.notifyDataSetChanged()
                } else {
                    println(call)
                    showError(call.code().toString())
                }
            }
        }
    }

    private fun initRecyclerView() {
        trackAdapter = TrackAdapter(searchResults)
        binding.trackRecycler.layoutManager = LinearLayoutManager(activity)
        binding.trackRecycler.adapter = trackAdapter
    }

    private fun showError(error: String) {
        Toast.makeText(activity, "An error occurred $error", Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchViewBinding.inflate(inflater, container, false)
        initRecyclerView()
        binding.searchView.setOnQueryTextListener(this)
        return binding.root
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchElement(query)
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}