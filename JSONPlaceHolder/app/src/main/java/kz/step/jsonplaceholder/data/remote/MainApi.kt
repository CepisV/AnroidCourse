package kz.step.jsonplaceholder.data.remote

import kz.step.jsonplaceholder.data.entity.AlbumRemote
import kz.step.jsonplaceholder.domain.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Post

    @GET("comments")
    suspend fun getPostComments(@Query("postId") postId: Int): List<Comment>

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: Int): User

    @GET("albums")
    suspend fun getAlbums(): List<AlbumRemote>

    @GET("photos")
    suspend fun getAlbumPhotos(@Query("albumId") albumId: Int): List<Photo>

    @GET("photos")
    suspend fun getAllPhotos(): List<Photo>

    @GET("todos")
    suspend fun getUserToDos(@Query("userId") userId: Int) : List<ToDo>

}