package com.mwabonje.marvelworld.view.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mwabonje.marvelworld.database.MarvelEntity
import com.mwabonje.marvelworld.databinding.FragmentCharacterListBinding
import com.mwabonje.marvelworld.network.Status
import com.mwabonje.marvelworld.viewmodel.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterListFragment] factory method to
 * create an instance of this fragment.
 */
class CharacterListFragment : DaggerFragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CharactersAdapter(listOf(), CharacterListener {
            navigate(it)
        })
        binding.rvCharacters.adapter = adapter

        viewModel.marvelCharacters.observe(viewLifecycleOwner) { result ->
            result?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressCircular.visibility = View.GONE
                        Log.d("KANDO", "${resource.data}")
                        adapter.submitList(resource.data ?: listOf())
                    }
                    Status.ERROR -> {
                        binding.progressCircular.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

    private fun navigate(character: MarvelEntity) {
        //todo: send request to get character's details
        Toast.makeText(requireContext(), character.characterName, Toast.LENGTH_SHORT).show()
//        findNavController().navigate(R.id.fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}