package kz.step.jsonplaceholder.data.remote

import kz.step.jsonplaceholder.domain.models.Album
import retrofit2.http.GET

interface PhotoApi {
    @GET("albums")
    suspend fun getAlbums(): List<Album>
}
