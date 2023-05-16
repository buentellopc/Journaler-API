package com.bpc.journalerapi.service

import com.bpc.journalerapi.data.Todo
import com.bpc.journalerapi.data.TodoDTO
import com.bpc.journalerapi.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service("Todo Service")
class TodoService {

    @Autowired
    lateinit var repository: TodoRepository


    fun getTodos(): Iterable<TodoDTO> = repository.findAll().map { todo -> TodoDTO(todo)
     }

    fun getScheduledLaterThan(date: Date): Iterable<TodoDTO> {
        return repository.findScheduledLaterThan(date.time).map { todo -> TodoDTO(todo) }
    }

    fun insertTodo(todo: TodoDTO): TodoDTO = TodoDTO(repository.save(Todo(title = todo.title, message = todo.message,
        location = todo.location,
        schedule = todo.schedule  ))
    )

    fun deleteTodo(id: String) = repository.deleteById(id)

    fun updateTodo(todoDTO: TodoDTO): TodoDTO {
      var todo = repository.findById(todoDTO.id).get()

        todo.title = todoDTO.title
        todo.message = todoDTO.message
        todo.location = todoDTO.location
        todo.schedule = todoDTO.schedule
        todo.modified = Date()
        todo = repository.save(todo)
        return TodoDTO(todo)
    }

    fun deleteAllTodos() = repository.deleteAll()

}



