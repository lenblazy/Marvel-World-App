package com.mwabonje.marvelworld.view.fragments.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mwabonje.marvelworld.database.MarvelEntity
import com.mwabonje.marvelworld.databinding.FragmentCharacterDetailBinding
import com.mwabonje.marvelworld.network.Status
import com.mwabonje.marvelworld.viewmodel.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

private const val CHARACTER = "character"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterDetailFragment] factory method to
 * create an instance of this fragment.
 */
class CharacterDetailFragment : DaggerFragment() {

    private var character: MarvelEntity? = null

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: MainViewModel

    private var detailsList = mutableListOf<DetailRow>()

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
        val adapter = CharacterDetailAdapter(detailsList)

        binding.apply {
            rvDetails.adapter = adapter
            tvTitle.text = character?.characterName
            val desc = character?.characterDescription ?: ""
            if (desc.isEmpty()){
                tvDescription.text = "No description Available"
            }else{
                tvDescription.text = desc
            }

            viewModel.characterDetails(character?.id.toString())
                .observe(viewLifecycleOwner) { result ->

                    result?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                val data = resource.data
                                val comicsList = data?.comics?.comics
                                val seriesList = data?.series?.series
                                val storyList = data?.stories?.stories
                                //
                                detailsList.clear()

                                detailsList.add(DetailRow.Header("Comics"))
                                comicsList?.forEach {
                                    detailsList.add(DetailRow.Detail(it.name))
                                }

                                detailsList.add(DetailRow.Header("Series"))
                                seriesList?.forEach {
                                    detailsList.add(DetailRow.Detail(it.name))
                                }

                                detailsList.add(DetailRow.Header("Stories"))
                                storyList?.forEach {
                                    detailsList.add(DetailRow.Detail(it.name))
                                }

                                binding.progressCircular.visibility = View.GONE
                                adapter.notifyDataSetChanged()
                            }
                            Status.ERROR -> {
                            binding.progressCircular.visibility = View.GONE
                                Toast.makeText(requireContext(), resource.message ?: "An Error occurred", Toast.LENGTH_SHORT).show()
                            }
                            Status.LOADING -> {
                            binding.progressCircular.visibility = View.VISIBLE
                            }
                        }
                    }

                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}