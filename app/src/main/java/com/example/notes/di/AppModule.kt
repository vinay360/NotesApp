package com.example.notes.di

import android.app.Application
import androidx.room.Room
import com.example.notes.feature_note.data.repository.NoteRepositoryImpl
import com.example.notes.feature_note.data.source.NoteDatabase
import com.example.notes.feature_note.domain.repository.NoteRepository
import com.example.notes.feature_note.domain.use_case.AddNote
import com.example.notes.feature_note.domain.use_case.DeleteNote
import com.example.notes.feature_note.domain.use_case.GetNotes
import com.example.notes.feature_note.domain.use_case.OrderNotes
import com.example.notes.feature_note.domain.use_case.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository) : UseCases {
        return UseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            orderNotes = OrderNotes()
        )
    }
}