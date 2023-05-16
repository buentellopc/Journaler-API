package com.bpc.journalerapi.service

import com.bpc.journalerapi.data.Note
import com.bpc.journalerapi.data.NoteDTO
import com.bpc.journalerapi.data.TodoDTO
import com.bpc.journalerapi.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service("Note Service")
class NoteService {

    @Autowired
    lateinit var repository: NoteRepository

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    fun getNotes(): Iterable<NoteDTO> = repository.findAll().map { note -> NoteDTO(note) }

    fun findByTitle(title: String): Iterable<NoteDTO> = repository.findByTitle(title).map { note -> NoteDTO(note) }

    /**
     * Saves a given entity. Use the returned instance for further operations as
     * the save operation might have changed the entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    fun insertNote(note: NoteDTO) = NoteDTO(
        repository.save(
            Note(
                title = note.title,
                message = note.message,
                location = note.location
            )
        )
    )
    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    fun deleteNote(id: String) = repository.deleteById(id)

    /**
     * Saves a given entity. Use the returned instance for further operations as
     * the save operation might have changed the entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    fun updateNote(noteDto: NoteDTO): NoteDTO {
        var note = repository.findById(noteDto.id).get()
        note.title = noteDto.title
        note.message = noteDto.message
        note.location = noteDto.location
        note.modified = Date()
        note = repository.save(note)
        return NoteDTO(note)
    }

}