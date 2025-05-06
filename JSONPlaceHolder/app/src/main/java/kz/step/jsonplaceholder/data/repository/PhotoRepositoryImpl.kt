package kz.step.jsonplaceholder.data.repository

import kz.step.jsonplaceholder.domain.models.Album
import kz.step.jsonplaceholder.domain.repository.PhotoRepository
import kz.step.jsonplaceholder.data.remote.PhotoApi

class PhotoRepositoryImpl(private val photoApi: PhotoApi) : PhotoRepository {
    override suspend fun getAlbums(): List<Album> {
        return photoApi.getAlbums()
    }
}
