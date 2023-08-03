package com.davutkarakus.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davutkarakus.rickandmortyapp.R
import com.davutkarakus.rickandmortyapp.databinding.RecyclerRowBinding
import com.davutkarakus.rickandmortyapp.model.Result

class MainRecyclerViewAdapter(private var characterList:ArrayList<Result>, private val listener:OnItemClickListener) :  RecyclerView.Adapter<MainRecyclerViewAdapter.RvHolder>(),CharacterClickListener{
    class RvHolder(var view:RecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerRowBinding>(inflater, R.layout.recycler_row,parent,false)
        return RvHolder(view)
    }
    override fun onBindViewHolder(holder: RvHolder, position: Int) {
        holder.view.character = characterList[position]
        holder.view.listener = this
        holder.view.root.tag = holder.view
    }
    override fun getItemCount(): Int {
        return characterList.size
    }
    fun updateCharacterList(newCharacterList:List<Result>){
        characterList.clear()
        characterList.addAll(newCharacterList)
    }
    override fun onCharacterClicked(v: View) {
        listener.onItemClickListenerCharacters(v)
    }
    interface OnItemClickListener {
        fun onItemClickListenerCharacters(v:View)
    }
}