package io.mkth.security.restpagination.repository

import io.mkth.security.restpagination.model.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveMongoRepository<User, String> {

    fun findByUsername(username: String) : Mono<User>

}