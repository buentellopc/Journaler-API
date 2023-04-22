package com.bpc.journalerapi.controller

import com.bpc.journalerapi.data.Todo
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/todos")
class TodoController {
    /**
     * Get todos.
     * Test with: curl -X GET http://localhost:9000/todos/obtain
     */
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTodos(): List<Todo> {
        return listOf(
                Todo(
                        UUID.randomUUID().toString(),
                        "My first todo",
                        "This is a message for the 1st todo.",
                        System.currentTimeMillis()
                ),
                Todo(
                        UUID.randomUUID().toString(),
                        "My second todo",
                        "This is a message for the 2nd todo.",
                        System.currentTimeMillis()
                )
        )

    }

    /**
     * Insert todo.
     * It consumes JSON, that is: request body Todo.
     */
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun insertTodo(@RequestBody todo: Todo): Todo {
        todo.id = UUID.randomUUID().toString()
        return todo
    }

    /**
     * Update todo.
     * It consumes JSON, that is: request body Todo.
     * As a result it returns updated todo
     */
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateTodo(
            @RequestBody todo: Todo
    ): Todo {
        todo.message += " Updated"
        todo.schedule = System.currentTimeMillis()
        return todo
    }

    /**
     * Delete todo by Id.
     * We introduce path variable for todo id.
     * As a result it returns true or false.
     */
    @DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteTodo(@PathVariable(name = "id") id: String): Boolean {
        println("Removing: $id")
        return true
    }




}

