package com.davutkarakus.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.davutkarakus.rickandmortyapp.databinding.RecyclerRowBinding
import com.davutkarakus.rickandmortyapp.model.Characters
import com.davutkarakus.rickandmortyapp.model.Result
import com.davutkarakus.rickandmortyapp.util.downloadFromUrl
import com.davutkarakus.rickandmortyapp.util.placeholderProgressBar
import com.davutkarakus.rickandmortyapp.view.FeedFragment
import com.davutkarakus.rickandmortyapp.view.FeedFragmentDirections

class RecyclerAdapter(var characterList:ArrayList<Result>) :  RecyclerView.Adapter<RecyclerAdapter.RvHolder>(){
    private lateinit var binding : RecyclerRowBinding
    class RvHolder(var view:RecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RecyclerRowBinding.inflate(inflater,parent,false)
        return RvHolder(binding)
    }

    override fun onBindViewHolder(holder: RvHolder, position: Int) {
        holder.view.nameText.text = characterList[position].name
        holder.view.speciesText.text = characterList[position].species
        holder.view.imageView.downloadFromUrl(characterList[position].image, placeholderProgressBar(holder.itemView.context))
        holder.itemView.setOnClickListener {
            characterList[position].id?.let { id->
                val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(id)
                Navigation.findNavController(it).navigate(action)
            }

        }
    }
    override fun getItemCount(): Int {
        return characterList.size
    }
    fun updateCountryList(newCharacterList:List<Result>){
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }
}