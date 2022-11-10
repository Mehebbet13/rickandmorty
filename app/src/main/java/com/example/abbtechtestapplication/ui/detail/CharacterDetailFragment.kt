package com.example.abbtechtestapplication.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.abbtechtestapplication.R
import com.example.abbtechtestapplication.databinding.FragmentCharacterDetailBinding
import com.example.abbtechtestapplication.models.Character
import com.example.abbtechtestapplication.ui.main.AllCharactersFragment.Companion.ID
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    val characterId by lazy { arguments?.getInt(ID) ?: DEFAULT_ID }
    lateinit var binding: FragmentCharacterDetailBinding
    private val viewModel: CharacterDetailViewModel by viewModels({ this })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacterById(characterId)
        viewModel.character.observe(viewLifecycleOwner){
            setData(it)

        }
    }

    private fun setData(character:Character){
        binding.apply {
            name.text = character.name
            gender.text = StringBuilder().append("Gender : ").append(character.gender)
            status.text = StringBuilder().append(character.status).append(" - ").append(character.species)
            type.text = StringBuilder().append(character.type).append(" - ").append(character.origin.name)
            Picasso.get().load(character.image).into(avatar)
        }
    }
    companion object {
        const val DEFAULT_ID = 0
    }
}