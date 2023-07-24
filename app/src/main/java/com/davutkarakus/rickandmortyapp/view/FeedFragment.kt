package com.davutkarakus.rickandmortyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.davutkarakus.rickandmortyapp.adapter.MainRecyclerViewAdapter
import com.davutkarakus.rickandmortyapp.databinding.FragmentFeedBinding
import com.davutkarakus.rickandmortyapp.databinding.RecyclerRowBinding
import com.davutkarakus.rickandmortyapp.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
 class FeedFragment @Inject constructor(
)  : Fragment() ,MainRecyclerViewAdapter.OnItemClickListener{

    private lateinit var binding : FragmentFeedBinding
    private lateinit var viewModel : FeedViewModel
    private val mainRecyclerViewAdapter = MainRecyclerViewAdapter(arrayListOf(),this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        binding.characterList.layoutManager = GridLayoutManager(context,2)
        binding.characterList.adapter = mainRecyclerViewAdapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        observeData()
       // viewModel.refreshData()
     //   observeLiveData()
    }
    private fun observeData() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            mainRecyclerViewAdapter.updateCharacterList(it.results ?: listOf())
        })
        }
    override fun onItemClickListenerMovies(v:View) {
        val binding = v.tag as? RecyclerRowBinding ?: return
        val uuid = binding.character?.id ?: return
        val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
/*    private fun observeLiveData() {
        viewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            characters?.let {
                binding.characterList.visibility = View.VISIBLE
                mainRecyclerViewAdapter.updateCharacterList(it.results ?: listOf())
                binding.errorText.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
        })
        viewModel.charactersLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterList.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                }else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
        viewModel.charactersError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it) {
                    binding.errorText.visibility = View.VISIBLE
                    binding.characterList.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }else {
                    binding.errorText.visibility = View.GONE
                }
            }
        })
    }
    */
}