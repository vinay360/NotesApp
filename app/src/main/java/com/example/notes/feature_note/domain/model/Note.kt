package com.example.notes.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notes.ui.theme.Cream
import com.example.notes.ui.theme.LightPurple
import com.example.notes.ui.theme.Peach
import com.example.notes.ui.theme.Sage

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(Cream, Sage, Peach, LightPurple)
    }
}
