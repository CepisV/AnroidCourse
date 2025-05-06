package kz.step.jsonplaceholder.domain.repository

import kz.step.jsonplaceholder.domain.models.User

interface UserRepository {
    suspend fun getUser(userId: Int): User

    suspend fun getUsers() : List<User>
}