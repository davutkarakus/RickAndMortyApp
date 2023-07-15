package com.davutkarakus.rickandmortyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.davutkarakus.rickandmortyapp.R
import com.davutkarakus.rickandmortyapp.adapter.RecyclerAdapter
import com.davutkarakus.rickandmortyapp.databinding.FragmentDetailBinding
import com.davutkarakus.rickandmortyapp.databinding.FragmentFeedBinding
import com.davutkarakus.rickandmortyapp.util.downloadFromUrl
import com.davutkarakus.rickandmortyapp.util.placeholderProgressBar
import com.davutkarakus.rickandmortyapp.viewmodel.DetailViewModel

class DetailFragment : Fragment() {
    private lateinit var viewModel : DetailViewModel
    private lateinit var binding : FragmentDetailBinding
    private var charId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            charId = DetailFragmentArgs.fromBundle(it).charId
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.refreshData(charId)
        detailObserveLiveData()
    }

    fun detailObserveLiveData() {
        viewModel.char.observe(viewLifecycleOwner, Observer { results->
            results?.let {
                var newList : List<String>
                it.episode?.let {
                    newList = it
                    for (i in newList) {
                        newList = i.split("https://rickandmortyapi.com/api/episode/")
                        for (a in newList) {
                            binding.detailEpisodeText.append("${a} ")
                        }
                    }
                }

                binding.detailCharNameText.text = it.name
                binding.detailOriginText.text = "Origin -> ${it.origin!!.name}"
                binding.detailGenderText.text = "Gender -> ${it.gender}"
                binding.detailLocationText.text = "Location -> ${it.location!!.name}"
                binding.detailStatusText.text = "Status -> ${it.status}"
                binding.detailSpeciesText.text = "Species -> ${it.species}"
                context?.let { context->
                    binding.detailImageView.downloadFromUrl(it.image, placeholderProgressBar(context))
                }
                binding.detailCharNameText.visibility = View.VISIBLE
                binding.cardView.visibility = View.VISIBLE
                binding.cardView1.visibility = View.VISIBLE
                binding.cardView2.visibility = View.VISIBLE
                binding.cardView3.visibility = View.VISIBLE
                binding.cardView4.visibility = View.VISIBLE
                binding.cardView5.visibility = View.VISIBLE
            }
        })
        viewModel.charLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it) {
                    binding.detailCharNameText.visibility = View.GONE
                    binding.cardView.visibility = View.GONE
                    binding.cardView1.visibility = View.GONE
                    binding.cardView2.visibility = View.GONE
                    binding.cardView3.visibility = View.GONE
                    binding.cardView4.visibility = View.GONE
                    binding.cardView5.visibility = View.GONE
                    binding.detailProgressBar.visibility = View.VISIBLE
                    binding.detailErrorText.visibility = View.GONE
                }else {
                    binding.detailProgressBar.visibility = View.GONE
                }
            }
        })
        viewModel.charError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it) {
                    binding.cardView1.visibility = View.GONE
                    binding.cardView2.visibility = View.GONE
                    binding.cardView3.visibility = View.GONE
                    binding.cardView4.visibility = View.GONE
                    binding.cardView5.visibility = View.GONE
                    binding.detailProgressBar.visibility = View.GONE
                    binding.detailErrorText.visibility = View.VISIBLE
                }else {
                    binding.detailErrorText.visibility = View.GONE
                }

            }
        })
    }

}