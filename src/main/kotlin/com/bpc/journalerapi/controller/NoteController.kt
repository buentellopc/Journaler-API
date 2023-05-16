package com.bpc.journalerapi.controller

import com.bpc.journalerapi.data.Note
import com.bpc.journalerapi.data.NoteDTO
import com.bpc.journalerapi.data.NoteFindByTitleRequest
import com.bpc.journalerapi.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/notes")
// TODO: What does this annotation do?
class NoteController {

    @Autowired
    private lateinit var service: NoteService
    /**
     * Get notes.
     * Test with: curl -X GET http://localhost:9000/notes
     */
    @GetMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNotes(): Iterable<NoteDTO> {
        return service.getNotes()
    }

    @GetMapping(
         "/by_title",
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
        consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNoteByTitle(
        @RequestBody payload: NoteFindByTitleRequest
    ): Iterable<NoteDTO> = service.findByTitle(payload.title)



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
            @RequestBody note: NoteDTO
    ): NoteDTO {
        return service.insertNote(note)
    }
    /**
     * Update note.
     * It consumes JSON, that is: request body Note.
     */
    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun updateNote(@RequestBody note: NoteDTO): NoteDTO = service.updateNote(note)

    /**
     * Delete note.
     * It consumes JSON, that is: request body Note.
     * Test with curl -X DELETE http://localhost:8080/notes/delete/123
     */
    @DeleteMapping("/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun deleteNote(@PathVariable(name = "id") id: String) = service.deleteNote(id)


}