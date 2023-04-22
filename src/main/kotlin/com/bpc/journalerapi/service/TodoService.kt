package com.bpc.journalerapi.service

import com.bpc.journalerapi.data.Todo
import org.springframework.stereotype.Service
import java.util.UUID

@Service("Todo Service")
class TodoService {
    fun getTodos(): List<Todo> = listOf(
        Todo(
            UUID.randomUUID().toString(),
            "My first todo",
            "This is a message for the first todo",
            System.currentTimeMillis()
        ),

        Todo(
            UUID.randomUUID().toString(),
            "My first todo",
            "This is a message for the second todo",
            System.currentTimeMillis()
        )
    )

    fun insertTodo(todo: Todo): Todo {
        todo.id = UUID.randomUUID().toString()
        return todo
    }

    fun deleteTodo(id: String): Boolean = false

    fun updateTodo(todo: Todo): Boolean = false


}