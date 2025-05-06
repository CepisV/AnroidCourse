package kz.step.jsonplaceholder.data.repository

import kz.step.jsonplaceholder.data.remote.MainApi
import kz.step.jsonplaceholder.domain.models.User
import kz.step.jsonplaceholder.domain.repository.UserRepository

class UserRepositoryImpl(private val mainApi: MainApi) : UserRepository {
    override suspend fun getUser(userId: Int): User {
        return mainApi.getUser(userId)
    }

    override suspend fun getUsers() : List<User> {
        return mainApi.getUsers()
    }
}