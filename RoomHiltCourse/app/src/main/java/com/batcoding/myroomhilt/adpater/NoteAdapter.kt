package com.batcoding.myroomhilt.adpater

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.batcoding.myroomhilt.databinding.ActivityAddNoteBinding
import com.batcoding.myroomhilt.databinding.ItemNoteBinding
import com.batcoding.myroomhilt.db.NoteEntity
import com.batcoding.myroomhilt.ui.UpdateNoteActivity
import com.batcoding.myroomhilt.util.Constants
import com.batcoding.myroomhilt.util.Constants.BUNDLE_NOTE_ID
import javax.inject.Inject

class NoteAdapter @Inject constructor(): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private lateinit var binding: ItemNoteBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemNoteBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class ViewHolder: RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item: NoteEntity){
            binding.apply {
                tvTitle.text = item.noteTitle
                tvDesc.text = item.noteDesc
                root.setOnClickListener {
                    val intent = Intent(context, UpdateNoteActivity::class.java)
                    intent.putExtra(BUNDLE_NOTE_ID, item.noteId)
                    context.startActivity(intent)
                }
            }
        }
    }

    private val differCallback = object :
    DiffUtil.ItemCallback<NoteEntity>(){
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}
