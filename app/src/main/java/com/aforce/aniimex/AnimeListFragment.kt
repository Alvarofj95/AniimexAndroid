package com.aforce.aniimex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aforce.aniimex.databinding.FragmentAnimeListBinding
import com.aforce.aniimex.model.Anime
import com.aforce.aniimex.network.AnimeApi
import javax.security.auth.callback.Callback

class AnimeListFragment : Fragment() {

    private var _binding: FragmentAnimeListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter = AnimeAdapter{onAnimeClick(it._id)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun requestData() {
        AnimeApi.service.getAnimes().enqueue(object : Callback<List<Anime>>)
    }

    private fun onAnimeClick(id:String) {
        val action =
            AnimeListFragmentDirections
                .actionAnimeListFragmentToAnimeDetailFragment(id)
        findNavController().navigate(action)
    }


}