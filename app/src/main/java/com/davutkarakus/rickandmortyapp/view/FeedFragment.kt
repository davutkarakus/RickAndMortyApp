package com.davutkarakus.rickandmortyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.davutkarakus.rickandmortyapp.R
import com.davutkarakus.rickandmortyapp.adapter.RecyclerAdapter
import com.davutkarakus.rickandmortyapp.databinding.FragmentFeedBinding
import com.davutkarakus.rickandmortyapp.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private lateinit var binding : FragmentFeedBinding
    private lateinit var viewModel : FeedViewModel
    private val recyclerAdapter = RecyclerAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()
        binding.characterList.layoutManager = GridLayoutManager(context,2)
        binding.characterList.adapter = recyclerAdapter
        observeLiveData()
    }
    fun observeLiveData() {
        viewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            characters?.let {
                binding.characterList.visibility = View.VISIBLE
                recyclerAdapter.updateCountryList(it.results!!)
                binding.errorText.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
        })
        viewModel.charactersLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                binding.progressBar.visibility = View.VISIBLE
                binding.characterList.visibility = View.GONE
                binding.errorText.visibility = View.GONE
            }
        })
        viewModel.charactersError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                binding.errorText.visibility = View.VISIBLE
                binding.characterList.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }

        })
    }

}