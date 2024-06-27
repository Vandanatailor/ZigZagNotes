package com.example.zigzagnotes.ui.adpter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Color.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.zigzagnotes.R
import com.example.zigzagnotes.databinding.ListNotesBinding
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.util.Constants
import com.example.zigzagnotes.util.ItemsCLickListner

class NotesAdapter ( var  context : Context,
     var list: MutableList<NoteModel>,
    var itemsCLickListner: ItemsCLickListner
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val colors = listOf(
        R.color.color1,
        R.color.color2,
        R.color.color3,
        R.color.color4,
        R.color.color5,
        R.color.color6,
        R.color.color7,
        R.color.color8,
        R.color.color9,
        R.color.color10
    )

    inner class ViewHolder(val binding: ListNotesBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListNotesBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.text=list[position].title
        holder.binding.description.text=list[position].description
        val randomColor = colors.random()
        val color = ContextCompat.getColor(holder.itemView.context, randomColor)
        holder.binding.lnMainBg.backgroundTintList = ColorStateList.valueOf(color)

        holder.binding.lnMainBg.setOnLongClickListener{
            holder.binding.ivDelete.visibility= View.VISIBLE
            holder.binding.lnMainBg.backgroundTintList=ColorStateList.valueOf(Color.RED)
            holder.binding.title.visibility=View.GONE
            holder.binding.description.visibility=View.GONE
            return@setOnLongClickListener true
        }
        holder.binding.ivDelete.setOnClickListener {
            itemsCLickListner.selectItemCLick(holder.adapterPosition,Constants.Delete)
        }

        holder.binding.lnMainBg.setOnClickListener {
            if(holder.binding.ivDelete.visibility==View.VISIBLE){
                holder.binding.ivDelete.visibility=View.GONE
                holder.binding.title.visibility=View.VISIBLE
                holder.binding.description.visibility=View.VISIBLE
                holder.binding.lnMainBg.backgroundTintList = ColorStateList.valueOf(color)
            }else{
                itemsCLickListner.selectItemCLick(holder.adapterPosition,Constants.DataShow)
            }
        }

    }
}