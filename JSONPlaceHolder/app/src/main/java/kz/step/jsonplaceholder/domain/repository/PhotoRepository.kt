package kz.step.jsonplaceholder.domain.repository

import kz.step.jsonplaceholder.domain.models.Album

interface PhotoRepository {
    suspend fun getAlbums(): List<Album>
}
