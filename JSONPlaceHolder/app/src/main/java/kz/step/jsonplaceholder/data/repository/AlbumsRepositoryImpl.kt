package kz.step.jsonplaceholder.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kz.step.jsonplaceholder.data.mapper.toAlbum
import kz.step.jsonplaceholder.data.remote.MainApi
import kz.step.jsonplaceholder.domain.models.Album
import kz.step.jsonplaceholder.domain.repository.AlbumsRepository

class AlbumsRepositoryImpl(private val mainApi: MainApi) : AlbumsRepository {

    override suspend fun getAlbums(): List<Album> = coroutineScope {
        val albumsDeferred = async { mainApi.getAlbums() }
        val usersDeferred = async { mainApi.getUsers() }
        val photosDeferred = async { mainApi.getAllPhotos() }

        val remoteAlbums = albumsDeferred.await()
        val users = usersDeferred.await()
        val photos = photosDeferred.await()

        remoteAlbums.map { remoteAlbum ->
            val correspondingUser = users.find { user -> user.id == remoteAlbum.userId }
            val correspondingPhoto = photos.find { photo -> photo.albumId == remoteAlbum.id }
            toAlbum(remoteAlbum = remoteAlbum, user = correspondingUser, photo = correspondingPhoto)
        }
    }
}
