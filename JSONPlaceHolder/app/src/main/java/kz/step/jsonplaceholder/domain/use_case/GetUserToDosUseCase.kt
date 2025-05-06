package kz.step.jsonplaceholder.domain.use_case

import kz.step.jsonplaceholder.domain.models.ToDo
import kz.step.jsonplaceholder.domain.repository.ToDosRepository

class GetUserToDosUseCase(private val toDosRepository: ToDosRepository) {
    suspend operator fun invoke(userId : Int) : List<ToDo> {
        return toDosRepository.getUserToDos(userId)
    }
}