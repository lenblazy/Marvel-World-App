package com.mwabonje.marvelworld.view.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mwabonje.marvelworld.R
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
        val adapter = CharactersAdapter(mutableListOf(), CharacterListener {
            navigate(it)
        })
        binding.rvCharacters.adapter = adapter

        viewModel.marvelCharacters.observe(viewLifecycleOwner) { result ->
            result?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressCircular.visibility = View.GONE
                        binding.rvCharacters.visibility = View.VISIBLE
                        adapter.setCharactersList(resource.data ?: mutableListOf())
                    }
                    Status.ERROR -> {
                        binding.progressCircular.visibility = View.GONE
//                        showError(resource.message ?: "An Error occurred")
                    }
                    Status.LOADING -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

//    private fun showError(msg: String){
//        val builder = AlertDialog.Builder(requireContext())
//
//        builder.setTitle("Error")
//        builder.setMessage(msg)
//
//        builder.setNegativeButton("Close") { dialog, which -> }
//
//        builder.show()
//    }

    private fun navigate(character: MarvelEntity) {
        val args = Bundle()
        args.putParcelable("character", character)
        findNavController().navigate(R.id.characterDetailFragment, args)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}