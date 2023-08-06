package com.example.notes.feature_note.data.repository

import com.example.notes.feature_note.data.source.NoteDao
import com.example.notes.feature_note.domain.model.Note
import com.example.notes.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun insertNode(note: Note) {
        return noteDao.insertNote(note)
    }

    override suspend fun deleteNode(note: Note) {
        return noteDao.deleteNote(note)
    }
}