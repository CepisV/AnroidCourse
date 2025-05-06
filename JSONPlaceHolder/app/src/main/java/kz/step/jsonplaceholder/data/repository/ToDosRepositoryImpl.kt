package kz.step.jsonplaceholder.data.repository

import kz.step.jsonplaceholder.data.remote.MainApi
import kz.step.jsonplaceholder.domain.models.ToDo
import kz.step.jsonplaceholder.domain.repository.ToDosRepository

class ToDosRepositoryImpl(private val mainApi: MainApi) : ToDosRepository {
    override suspend fun getUserToDos(userId : Int): List<ToDo> {
        return mainApi.getUserToDos(userId)
    }
}