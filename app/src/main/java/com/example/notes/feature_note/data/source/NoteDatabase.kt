package com.example.notes.feature_note.data.source

import androidx.room.Database
import com.example.notes.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase {
    abstract val noteDao: NoteDao
}