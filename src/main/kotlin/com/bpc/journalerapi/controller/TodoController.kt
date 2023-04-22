package com.bpc.journalerapi.controller

import com.bpc.journalerapi.data.Todo
import com.bpc.journalerapi.service.NoteService
import com.bpc.journalerapi.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/todos")
class TodoController {

    @Autowired
    private lateinit var service: TodoService
    /**
     * Get todos.
     * Test with: curl -X GET http://localhost:9000/todos/obtain
     */
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTodos(): List<Todo> = service.getTodos();



    /**
     * Insert todo.
     * It consumes JSON, that is: request body Todo.
     */
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun insertTodo(@RequestBody todo: Todo): Todo {
        return service.insertTodo(todo)
    }

    /**
     * Update todo.
     * It consumes JSON, that is: request body Todo.
     * As a result it returns updated todo
     */
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateTodo(
            @RequestBody todo: Todo
    ): Boolean = service.updateTodo(todo)

    /**
     * Delete todo by Id.
     * We introduce path variable for todo id.
     * As a result it returns true or false.
     */
    @DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteTodo(@PathVariable(name = "id") id: String): Boolean = service.deleteTodo(id)


}






