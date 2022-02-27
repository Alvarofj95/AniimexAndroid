package com.aforce.aniimex.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aforce.aniimex.databinding.FragmentAnimeDetailBinding
import com.aforce.aniimex.extensions.imageUrl
import com.aforce.aniimex.model.Anime
import com.aforce.aniimex.network.AnimeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeDetailFragment : Fragment() {
    private val args: AnimeDetailFragmentArgs by navArgs()
    private var _binding: FragmentAnimeDetailBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvId.text = args.id

        args.id?.let {
            requestData(it)
        } ?: showError("AnimeId Null")
    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun requestData(animeId: String) {
        AnimeApi.service.getAnimeById(animeId).enqueue(object : retrofit2.Callback<Anime>{
            override fun onResponse(call: Call<Anime>, response: Response<Anime>) {
                if (response.isSuccessful) {
                    populateUi(response.body())
                } else {
                    showError("Error en la respuesta")
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<Anime>, t: Throwable) {
                Log.e("requestData", "error", t)
                showError("Error en la conexión")
            }

        })
    }

    private fun populateUi(anime: Anime?) {
        anime?.let {
            binding.tvId.text = it.id
            binding.tvName.text = it.name
            binding.tvCategory.text = it.category
            binding.tvSeasons.text = it.seasons
            binding.tvDescription.text = it.description
            binding.ivAnime.imageUrl(it.url)
            binding.btnDeleteAnime.setOnClickListener{ view ->
                deleteAnime(it)
            }

        } ?: showError("Error to retrieve anime")
    }

    private fun deleteAnime(anime: Anime) {
        AnimeApi.service.deleteAnimeById(anime.id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    findNavController().popBackStack()
                } else {
                    showError("Error al borrar la categoria con id: ${anime.id}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("requestData", "error", t)
                showError("Error en la conexión al borrar la tecnología")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}