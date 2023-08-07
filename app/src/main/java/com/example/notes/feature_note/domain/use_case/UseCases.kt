package com.example.notes.feature_note.domain.use_case

data class UseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val orderNotes: OrderNotes
)
