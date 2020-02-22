package tech.comfortheart.uaa.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import tech.comfortheart.uaa.tech.comfortheart.uaa.domain.User
import tech.comfortheart.uaa.tech.comfortheart.uaa.repository.UserRepo
import javax.inject.Inject

@Controller
class UserController {
    @Inject
    lateinit var userRepo: UserRepo

    @Get("/user")
    fun newUser(): List<User> {
        val user = User(name = "user1", password = "my password")
        userRepo.save(user)

        userRepo.save(User(name = "user2", password = "my password"))
        userRepo.save(User(name = "user2", password = "my password 2"))



        return userRepo.findByName("user2")
    }

    @Get("/user/test")
    fun test(): String {
        return "Hello"
    }
}