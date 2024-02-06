package com.batcoding.myroomhilt.repository

import android.provider.ContactsContract.CommonDataKinds.Note
import com.batcoding.myroomhilt.db.NoteDao
import com.batcoding.myroomhilt.db.NoteEntity
import javax.inject.Inject

class DbRepository
@Inject constructor(
    private val dao : NoteDao
)
{
    fun saveNote(note : NoteEntity) = dao.insertNote(note)
    fun updateNote(note : NoteEntity) = dao.updateNote(note)
    fun deleteNote(note : NoteEntity) = dao.deleteNote(note)
    fun getNOte(id : Int) = dao.getNote(id)
    fun getAllNotes() = dao.getAllNotes()
}