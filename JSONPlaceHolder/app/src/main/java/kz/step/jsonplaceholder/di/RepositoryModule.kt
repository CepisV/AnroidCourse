package kz.step.jsonplaceholder.di

import kz.step.jsonplaceholder.data.repository.*
import kz.step.jsonplaceholder.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    factory<PostsRepository> { PostsRepositoryImpl(get()) }
    factory<CommentsRepository> { CommentsRepositoryImpl(get()) }
    factory<UserRepository> { UserRepositoryImpl(get()) }
    factory<AlbumsRepository> { AlbumsRepositoryImpl(get()) }
    factory<ToDosRepository> { ToDosRepositoryImpl(get()) }
    factory<PhotoRepository> { PhotoRepositoryImpl(get()) }
}