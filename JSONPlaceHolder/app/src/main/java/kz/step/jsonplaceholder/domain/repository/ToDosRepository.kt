package kz.step.jsonplaceholder.domain.repository

import kz.step.jsonplaceholder.domain.models.ToDo

interface ToDosRepository {
    suspend fun getUserToDos(userId : Int) : List<ToDo>
}