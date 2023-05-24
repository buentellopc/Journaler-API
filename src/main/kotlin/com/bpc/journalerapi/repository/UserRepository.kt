package com.bpc.journalerapi.repository

import com.bpc.journalerapi.security.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String> {
    fun findOneByEmail(email: String): User?
}