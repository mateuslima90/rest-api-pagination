package io.mkth.security.restpagination.controller

import io.mkth.security.restpagination.model.User
import io.mkth.security.restpagination.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class UserController(val userService: UserService) {

    @GetMapping("/user/{username}")
    fun getUserByUsername(@PathVariable("username") username: String) : Mono<User> {
        return userService.getUserByUsername(username)
    }

    @GetMapping("/allUsers")
    fun getAllUser(@RequestParam("page") page: Int,
                   @RequestParam("size") size: Int): Mono<Page<User>> {
        return userService.findAllUsersPaged(PageRequest.of(page, size))
    }

    @PostMapping("/create/user")
    fun createUser(@RequestBody user: User) : Mono<User> {
        return userService.createUser(user)
    }
}