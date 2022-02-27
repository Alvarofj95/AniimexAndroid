package com.aforce.aniimex.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aforce.aniimex.databinding.FragmentAnimeAddBinding
import com.aforce.aniimex.model.AnimeBody
import com.aforce.aniimex.network.AnimeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeAddFragment : Fragment() {

    private var _binding: FragmentAnimeAddBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirm.setOnClickListener{
            if (validateFields()){
                sendAnimeToServer()
            }
        }
    }

    private fun validateFields(): Boolean {
        val animeName = binding.etName.text.toString()
        if (animeName.isEmpty()) {
            showError("AnimeName is empty")
            return false
        }

        val animeCategory = binding.etCategory.text.toString()
        if (animeCategory.isEmpty()) {
            showError("AnimeCategory is empty")
            return false
        }

        val animeSeasons = binding.etSeasons.text.toString()
        if (animeSeasons.isEmpty()) {
            showError("AnimeSeason is empty")
            return false
        }

        val animeDescription = binding.etDescription.text.toString()
        if (animeDescription.isEmpty()) {
            showError("AnimeDescription is empty")
            return false
        }

        val animeUrl = binding.etUrl.text.toString()
        if (animeUrl.isEmpty()) {
            showError("AnimeUrl is empty")
            return false
        }
        return true
    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun sendAnimeToServer() {

        val animeName = binding.etName.text.toString()
        val animeCategory = binding.etCategory.text.toString()
        val animeSeasons = binding.etSeasons.text.toString()
        val animeDescription = binding.etDescription.text.toString()
        val animeImageUrl = binding.etUrl.text.toString()

        val animeRequest = AnimeBody(animeCategory, animeDescription, animeName, animeSeasons, animeImageUrl)

        AnimeApi.service.createAnime(animeRequest).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Añadido correctamente", Toast.LENGTH_SHORT).show()
                    resetScreen()
                } else {
                    showError("Error en la respuesta")
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("requestData", "error", t)
                showError("Error en la conexión")
            }
        })

    }

    private fun resetScreen() {
        binding.etName.text = null
        binding.etCategory.text = null
        binding.etSeasons.text = null
        binding.etDescription.text = null
        binding.etUrl.text = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}