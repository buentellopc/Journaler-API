package com.bpc.journalerapi.controller

import com.bpc.journalerapi.data.Todo
import com.bpc.journalerapi.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoController {

    @Autowired
    private lateinit var service: TodoService
    /**
     * Get Todos.
     * Test with: curl -X GET http://localhost:9000/todos
     */
    @GetMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getTodos(): Iterable<Todo> {
        return service.getTodos()
    }

    /**
     * Insert Todo.
     * It consumes JSON, that is: request body Todo.
     */
    @PostMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
        consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertTodo(
        @RequestBody todo: Todo
    ): Todo = service.insertTodo(todo)

    /**
     * Update Todo.
     * It consumes JSON, that is: request body Todo.
     */
    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun updateTodo(@RequestBody todo: Todo): Todo = service.updateTodo(todo)

    /**
     * Delete Todo.
     * It consumes JSON, that is: request body Todo.
     * Test with curl -X DELETE http://localhost:9000/Todos/delete
     */
    @DeleteMapping("/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun deleteTodo(@PathVariable(name = "id") id: String) = service.deleteTodo(id)

    /**
     * Delete all Todos.
     * Test with curl -X DELETE http://localhost:9000/Todos/deleteAll
     */
    @DeleteMapping("/deleteAll", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun deleteAllTodos() = service.deleteAllTodos()


}






