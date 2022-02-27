package com.aforce.aniimex

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aforce.aniimex.databinding.FragmentAnimeListBinding
import com.aforce.aniimex.model.Anime
import com.aforce.aniimex.network.AnimeApi
import retrofit2.Response
import javax.security.auth.callback.Callback

class AnimeListFragment : Fragment() {

    private var _binding: FragmentAnimeListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter = AnimeAdapter{onAnimeClick(it.id)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAnimeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configUi()
        binding.rvAnime.adapter = adapter
        binding.rvAnime.layoutManager = LinearLayoutManager(context)

        requestData()

    }

    private fun requestData() {
        AnimeApi.service.getAnimes().enqueue(object : retrofit2.Callback<List<Anime>> {
            override fun onResponse(
                call: retrofit2.Call<List<Anime>>,
                response: Response<List<Anime>>
            ) {
                if (response.isSuccessful) {
                    adapter.submitList(response.body())
                } else {
                    Toast.makeText(context, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Anime>>, t: Throwable) {
                Toast.makeText(context, "Error en la conexión", Toast.LENGTH_SHORT).show()
                Log.e("requestData", "error", t)
            }

        })
    }

    private fun configUi() {
        binding.fabAddAnime.setOnClickListener{
            val action = AnimeListFragmentDirections.actionAnimeListFragmentToAnimeAddFragment()
            findNavController().navigate(action)
        }
    }

    private fun onAnimeClick(id:String) {
        val action =
            AnimeListFragmentDirections
                .actionAnimeListFragmentToAnimeDetailFragment(id)
        findNavController().navigate(action)
    }


}