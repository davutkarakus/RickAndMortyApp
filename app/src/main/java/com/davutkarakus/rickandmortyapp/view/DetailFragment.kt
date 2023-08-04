package com.davutkarakus.rickandmortyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.davutkarakus.rickandmortyapp.R
import com.davutkarakus.rickandmortyapp.databinding.FragmentDetailBinding
import com.davutkarakus.rickandmortyapp.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment @Inject constructor() : Fragment() {
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
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        getData()
    }
    private fun getData() {
        context?.let {
            viewModel.getData(charId, it)
        }
    }
}