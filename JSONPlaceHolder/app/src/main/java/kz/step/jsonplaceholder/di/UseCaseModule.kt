package kz.step.jsonplaceholder.di

import kz.step.jsonplaceholder.domain.use_case.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPostsUseCase(get()) }
    factory { GetPostByIdUseCase(get()) }
    factory { GetPostCommentsUseCase(get()) }
    factory { GetUserUseCase(get()) }
    factory { GetAlbumsUseCase(get()) }
    factory { GetUsersUseCase(get()) }
    factory { GetUserToDosUseCase(get()) }
}