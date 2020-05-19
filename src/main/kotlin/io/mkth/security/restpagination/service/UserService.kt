package io.mkth.security.restpagination.service

import io.mkth.security.restpagination.model.User
import io.mkth.security.restpagination.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.nio.file.attribute.UserPrincipalNotFoundException
import org.springframework.data.domain.PageImpl
import java.util.ArrayList



@Service
class UserService(val userRepository: UserRepository) {

    fun getUserByUsername(username: String) : Mono<User> {
        return userRepository.findByUsername(username)
                .onErrorResume { error -> throw UserPrincipalNotFoundException("User not found") }
    }

    fun findAllUsersPaged(pageable: Pageable): Mono<Page<User>> {
        return this.userRepository.count()
                .flatMap { userCount ->
                    this.userRepository.findAll(pageable.sort)
                            .buffer(pageable.pageSize, pageable.pageNumber + 1)
                            .elementAt(pageable.pageNumber, ArrayList<User>())
                            .map { users -> PageImpl(users, pageable, userCount.toLong()) }
                }
    }

    fun createUser(user: User) : Mono<User> {
        return userRepository.save(user)
    }
}