package com.jouernaler.journalerapi.controller

import com.jouernaler.journalerapi.data.Note
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/notes")
@EnableAutoConfiguration
class NoteController {

    @GetMapping("/obtain", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getNotes() : List<Note> {
        return listOf(Note(
                UUID.randomUUID().toString(),
            "My first note",
            "This is a messge for the 1st note"),
            Note(
                UUID.randomUUID().toString(),
                "My first note",
                "This is a messge for the 1st note"

        ))
    }
}