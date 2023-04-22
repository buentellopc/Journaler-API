package com.jouernaler.journalerapi.controller

import com.jouernaler.journalerapi.data.Note
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/notes")
// TODO: What does this annotation do?
//@EnableAutoConfiguration
class NoteController {
    /**
     * Get notes.
     * Test with: curl -X GET http://localhost:8080/notes/obtain
     */
    @GetMapping(
            "/obtain",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNotes(): List<Note> {
        return listOf(
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
    }

    /**
     * Insert note.
     * It consumes JSON, that is: request body Note.
     * Test with: curl -X POST -H "Content-Type: application/json" -d '{"title":"My third note","message":"This is a message for the 3rd note."}' http://localhost:8080/notes/insert
     */
    @PostMapping(
              "/insert",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertNote(
            @RequestBody note: Note
    ): Note {
        note.id = UUID.randomUUID().toString()
        return note
    }

    /**
     * Update note.
     * It consumes JSON, that is: request body Note.
     */
    @PutMapping("/update", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun updateNote(@RequestBody note: Note): Note {
        note.title += " - updated"
        return note
    }

    /**
     * Delete note.
     * It consumes JSON, that is: request body Note.
     * Test with curl -X DELETE http://localhost:8080/notes/delete/123
     */
    @DeleteMapping("/delete/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun deleteNote(@PathVariable(name = "id") id: String): Boolean {
        println("Removing: $id")
        return true
    }


}