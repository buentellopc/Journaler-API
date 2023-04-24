package com.bpc.journalerapi.controller

import com.bpc.journalerapi.data.Note
import com.bpc.journalerapi.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/notes")
// TODO: What does this annotation do?
//@EnableAutoConfiguration
class NoteController {

    @Autowired
    private lateinit var service: NoteService
    /**
     * Get notes.
     * Test with: curl -X GET http://localhost:8080/notes/obtain
     */
    @GetMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNotes(): Iterable<Note> {
        return service.getNotes()
    }

    /**
     * Insert note.
     * It consumes JSON, that is: request body Note.
     * Test with: curl -X POST -H "Content-Type: application/json" -d '{"title":"My third note","message":"This is a message for the 3rd note."}' http://localhost:8080/notes/insert
     */
    @PostMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertNote(
            @RequestBody note: Note
    ): Note = service.insertNote(note)

    /**
     * Update note.
     * It consumes JSON, that is: request body Note.
     */
    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun updateNote(@RequestBody note: Note): Note = service.updateNote(note)

    /**
     * Delete note.
     * It consumes JSON, that is: request body Note.
     * Test with curl -X DELETE http://localhost:8080/notes/delete/123
     */
    @DeleteMapping("/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun deleteNote(@PathVariable(name = "id") id: String) = service.deleteNote(id)


}