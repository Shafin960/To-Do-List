package com.example.help

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(val context: Context, val listener: INotesRVAdapter) : RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {
    val allNotes = ArrayList<Note>()
    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView= itemView.findViewById<TextView>(R.id.textView)
        val delete=itemView.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.delete.setOnClickListener {
            listener.onItemclicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=allNotes[position]
        holder.textView.text=currentNote.text
    }

    fun updateList(newList : List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    interface INotesRVAdapter{
        fun onItemclicked(note: Note)
    }
}