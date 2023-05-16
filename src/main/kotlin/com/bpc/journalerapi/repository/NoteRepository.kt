package com.bpc.journalerapi.repository
import com.bpc.journalerapi.data.Note
import org.springframework.data.repository.CrudRepository

/**
 * String is the type for ID we use.
 */
interface NoteRepository : CrudRepository<Note, String> {
    fun findByTitle(title: String): Iterable<Note>
}

