package com.bpc.journalerapi

import com.bpc.journalerapi.security.Admin
import com.bpc.journalerapi.security.Member
import com.bpc.journalerapi.security.UserDTO
import com.bpc.journalerapi.service.UserService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.util.Assert
import java.lang.System.out

@SpringBootTest
class SecurityInitializationTest {

    @Autowired
    private lateinit var userService: UserService

    private val password = "12345"
    private val adminEmail = "admin@example.com"
    private val memberEmail = "member@example.com"

    @Test
    fun initAdmin() {
        try {
            val admin = userService.loadUserByUsername(adminEmail)
            if (admin is Admin) {
                println("Admin user exists: ${admin.id}")
            } else {
print("Admin does not exist")            }
        } catch (e: RuntimeException) {
            val toSave = UserDTO(
                adminEmail,
                password,
                "admin",
                "admin"
            )
            val saved = userService.saveAdmin(toSave)
            println("Admin user inserted: ${saved.id}")
        }
    }

    @Test
    fun initMember() {
        try {
            val member = userService.loadUserByUsername(memberEmail)
            if (member is Member) {
                println("Member user exists: ${member.id}")
            } else {
                print("hi")
            }
        } catch (e: RuntimeException) {
            val toSave = UserDTO(
                memberEmail,
                password,
                "member",
                "member"
            )
            val saved = userService.saveMember(toSave)
            println("Member user inserted: ${saved.id}")
        }
    }
}