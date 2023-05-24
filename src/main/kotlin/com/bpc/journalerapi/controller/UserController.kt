package com.bpc.journalerapi.controller

import com.bpc.journalerapi.security.User
import com.bpc.journalerapi.security.UserDTO
import com.bpc.journalerapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.print.attribute.standard.Media

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    /**
     * Get Users.
     */
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUsers() = userService.getUsers()


    @PostMapping(value = ["/admin"], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun insertAdmin(@RequestBody user: UserDTO) = userService.saveAdmin(user)


    @PostMapping("/member", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun insertMember(@RequestBody user: UserDTO) = userService.saveMember(user)

    @DeleteMapping("/{id}",produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteUser(@PathVariable(name = "id") id: String) = userService.deleteUser(id)

    @PutMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
        consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateUser(
        @RequestBody user: User
    ): User? = userService.updateUser(user)

}