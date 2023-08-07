package com.example.notes.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.feature_note.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    init {
        noteUseCases.getNotes(noteOrder = state.value.noteOrder).onEach { notes ->
            _state.value = state.value.copy(notes = notes)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    _state.value = state.value.copy(recentlyDeleteNote = event.note)
                }
            }

            is NotesEvent.Order -> {
                _state.value = state.value.copy(
                    notes = noteUseCases.orderNotes(state.value.notes, event.noteOrder),
                    noteOrder = event.noteOrder
                )
            }

            NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(state.value.recentlyDeleteNote ?: return@launch)
                    _state.value = state.value.copy(recentlyDeleteNote = null)
                }
            }

            NotesEvent.ToogleOrderSection -> {
                _state.value =
                    state.value.copy(isOrderSectionVisible = !state.value.isOrderSectionVisible)
            }
        }
    }
}