package com.mwabonje.marvelworld.view.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mwabonje.marvelworld.databinding.FragmentCharacterDetailBinding
import com.mwabonje.marvelworld.models.DefaultResponse
import dagger.android.support.DaggerFragment

private const val CHARACTER = "character"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterDetailFragment] factory method to
 * create an instance of this fragment.
 */
class CharacterDetailFragment : DaggerFragment() {

    private var character: DefaultResponse? = null

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            character = it.getParcelable(CHARACTER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvTitle.text = ""
            tvDescription.text = ""
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}