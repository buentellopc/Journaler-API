package com.bpc.journalerapi.repository

import com.bpc.journalerapi.data.Todo
import org.springframework.data.repository.CrudRepository


interface TodoRepository : CrudRepository<Todo, String>