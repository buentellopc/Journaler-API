package com.bpc.journalerapi.service

import com.bpc.journalerapi.repository.UserRepository
import com.bpc.journalerapi.security.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service("User Service")
class UserService : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    val encoder = BCryptPasswordEncoder(11)

    override fun loadUserByUsername(email: String): User? {
        return userRepository.findOneByEmail(email) ?: throw RuntimeException("User not found: $email")
    }

    fun getUsers() = userRepository.findAll()
        .map { user: User -> UserDetailsDTO(user.id, user.email, user.firstName, user.lastName, user.roles) }


    fun saveAdmin(user: UserDTO): User {
        val admin = Admin()
        admin.email = user.email
        admin.firstName = user.firstName
        admin.lastName = user.lastName
        admin.roles = "ADMIN, MEMBER"
        admin.pwd = encoder.encode(user.password)
        return userRepository.save(admin)
    }

    fun saveMember(user: UserDTO): User {
        val member = Member()
        member.email = user.email
        member.firstName = user.firstName
        member.lastName = user.lastName
        member.roles = "MEMBER"
        member.pwd = encoder.encode(user.password)
        return userRepository.save(member)

    }

    fun deleteUser(id: String) = userRepository.deleteById(id)

    fun updateUser(toSave: User): User? {
        val user = userRepository.findOneByEmail(toSave.email)
        user?.let {
            if (toSave.pwd.isNotEmpty()) {
                user.pwd = encoder.encode(toSave.password)
            }
            user.firstName = toSave.firstName
            user.lastName = toSave.lastName
            user.accountNonExpired = toSave.accountNonExpired
            user.accountNonLocked = toSave.accountNonLocked
            user.credentialsNonExpired = toSave.credentialsNonExpired
            user.modified = Date()
            return userRepository.save(user)
        }
        return null
    }
}
