package com.batcoding.myroomhilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.compose.material3.Snackbar
import com.batcoding.myroomhilt.R
import com.batcoding.myroomhilt.adpater.NoteAdapter
import com.batcoding.myroomhilt.databinding.ActivityUpdateNoteBinding
import com.batcoding.myroomhilt.db.NoteEntity
import com.batcoding.myroomhilt.repository.DbRepository
import com.batcoding.myroomhilt.util.Constants.BUNDLE_NOTE_ID
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNoteBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var noteAdapter: NoteAdapter

    @Inject
    lateinit var noteEntity: NoteEntity

    private var noteId = 0
    private var defaultTitle = ""
    private var defaultDesc = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            noteId = it.getInt(BUNDLE_NOTE_ID)
        }

        binding.apply {
            defaultTitle = repository.getNOte(noteId).noteTitle
            defaultDesc = repository.getNOte(noteId).noteDesc

            //Setting data
            edtTitle.setText(defaultTitle)
            edtDesc.setText(defaultDesc)

            //Action for btnDelete
            btnDelete.setOnClickListener{
                noteEntity = NoteEntity(noteId, defaultTitle, defaultDesc)
                repository.deleteNote(noteEntity)
                finish()
            }
            //Action for btnUpdate
            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if (title.isNotEmpty() || desc.isNotEmpty()){
                    noteEntity = NoteEntity(noteId, title, desc)
                    repository.updateNote(noteEntity)
                    finish()//Finish activity and go back main
                }else{//Just show a message
                    Snackbar.make(it, getString(R.string.title_and_desc_cannot_be_empty), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}