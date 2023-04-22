package com.bpc.journalerapi.service

import com.bpc.journalerapi.data.Note
import org.springframework.stereotype.Service
import java.util.*


@Service("Note Service")
class NoteService {
    fun getNotes(): List<Note>  = listOf(
            Note(
                    UUID.randomUUID().toString(),
                    "My first note",
                    "This is a message for the 1st note."
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "My second note",
                    "This is a message for the 2nd note."
            )
    )

    fun insertNote(note: Note): Note {
        note.id = UUID.randomUUID().toString()
        return note;
    }

    fun deleteNote(id: String): Boolean = false

    fun updateNote(note: Note): Boolean = false

}