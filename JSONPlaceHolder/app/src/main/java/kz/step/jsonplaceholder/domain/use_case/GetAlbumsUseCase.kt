package kz.step.jsonplaceholder.domain.use_case

import kz.step.jsonplaceholder.domain.models.Album
import kz.step.jsonplaceholder.domain.repository.AlbumsRepository

class GetAlbumsUseCase(
    private val albumsRepository: AlbumsRepository
) {
    suspend operator fun invoke(): List<Album> {
        return albumsRepository.getAlbums()
    }
}