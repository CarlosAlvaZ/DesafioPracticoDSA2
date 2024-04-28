package com.example.desafiopracticoiidsa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.desafiopracticoiidsa2.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonTitle.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToSearchView(
                    searchType = SearchType.TITLE
                )
            )
        }
        binding.buttonAlbum.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToSearchView(
                    searchType = SearchType.ALBUM
                )
            )
        }
        binding.buttonArtist.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToSearchView(
                    searchType = SearchType.ARTIST
                )
            )
        }
        binding.buttonGenre.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToSearchView(
                    searchType = SearchType.GENRE
                )
            )
        }
    }
}