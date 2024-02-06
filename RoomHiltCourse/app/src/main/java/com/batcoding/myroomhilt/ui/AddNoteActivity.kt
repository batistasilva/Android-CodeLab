package com.batcoding.myroomhilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.compose.material3.Snackbar
import com.batcoding.myroomhilt.R
import com.batcoding.myroomhilt.databinding.ActivityAddNoteBinding
import com.batcoding.myroomhilt.databinding.ActivityMainBinding
import com.batcoding.myroomhilt.db.NoteEntity
import com.batcoding.myroomhilt.repository.DbRepository
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddNoteBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var noteEntity: NoteEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener{
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if (title.isNotEmpty() || desc.isNotEmpty()){
                    noteEntity = NoteEntity(0, title, desc)
                    repository.saveNote(noteEntity)
                    finish()//Finish activity and go back main
                }else{//Just show a message
                    Snackbar.make(it,
                        getString(R.string.title_and_desc_cannot_be_empty),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}