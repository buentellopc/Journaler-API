package com.bpc.journalerapi.service

import com.bpc.journalerapi.data.Todo
import com.bpc.journalerapi.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service("Todo Service")
class TodoService {

    @Autowired
    lateinit var repository: TodoRepository


    fun getTodos(): Iterable<Todo> = repository.findAll()

    fun insertTodo(todo: Todo): Todo = repository.save(todo)

    fun deleteTodo(id: String) = repository.deleteById(id)

    fun updateTodo(todo: Todo): Todo = repository.save(todo)

    fun deleteAllTodos() = repository.deleteAll()

}



