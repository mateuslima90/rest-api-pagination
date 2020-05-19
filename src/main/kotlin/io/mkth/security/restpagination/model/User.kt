package io.mkth.security.restpagination.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(@Id val id: String? = null,
                val username: String? = null,
                val password: String? = null)