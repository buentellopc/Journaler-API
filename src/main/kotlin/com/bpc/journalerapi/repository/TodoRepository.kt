package com.bpc.journalerapi.repository

import com.bpc.journalerapi.data.Todo
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.Date


interface TodoRepository : CrudRepository<Todo, String> {
    @Query("from Todo t where t.schedule > ?1")
    fun findScheduledLaterThan(date: Long): Iterable<Todo>
}