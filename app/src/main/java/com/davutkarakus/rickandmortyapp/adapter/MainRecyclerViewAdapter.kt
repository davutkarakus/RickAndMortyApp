package com.davutkarakus.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davutkarakus.rickandmortyapp.R
import com.davutkarakus.rickandmortyapp.databinding.RecyclerRowBinding
import com.davutkarakus.rickandmortyapp.model.Result

class MainRecyclerViewAdapter(var characterList:ArrayList<Result>,private val listener:OnItemClickListener) :  RecyclerView.Adapter<MainRecyclerViewAdapter.RvHolder>(),CharacterClickListener{
    // private lateinit var binding : RecyclerRowBinding
    class RvHolder(var view:RecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHolder {
        val inflater = LayoutInflater.from(parent.context)
        // binding = RecyclerRowBinding.inflate(inflater,parent,false)
        val view = DataBindingUtil.inflate<RecyclerRowBinding>(inflater, R.layout.recycler_row,parent,false)
        return RvHolder(view)
    }
    override fun onBindViewHolder(holder: RvHolder, position: Int) {
        holder.view.character = characterList[position]
        holder.view.listener = this
        holder.view.root.tag = holder.view
        /*
        holder.view.nameText.text = characterList[position].name
        holder.view.speciesText.text = characterList[position].species
        holder.view.imageView.downloadFromUrl(characterList[position].image, placeholderProgressBar(holder.itemView.context))
        holder.itemView.setOnClickListener {
            characterList[position].id?.let { id->
                val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(id)
                Navigation.findNavController(it).navigate(action)
            }
        }
         */
    }
    override fun getItemCount(): Int {
        return characterList.size
    }
    fun updateCharacterList(newCharacterList:List<Result>){
        characterList.clear()
        characterList.addAll(newCharacterList)
    }
    override fun onCharacterClicked(v: View) {
        listener.onItemClickListenerMovies(v)
    }
    interface OnItemClickListener {
        fun onItemClickListenerMovies(v:View)
    }
}