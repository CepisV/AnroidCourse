package kz.step.jsonplaceholder.data.mapper

import kz.step.jsonplaceholder.data.entity.AlbumRemote
import kz.step.jsonplaceholder.domain.models.Album
import kz.step.jsonplaceholder.domain.models.Photo
import kz.step.jsonplaceholder.domain.models.User

fun toAlbum(remoteAlbum: AlbumRemote, user: User? = null, photo: Photo? = null): Album {
    return Album(
        id = remoteAlbum.id,
        previewPhoto = photo?.thumbnailUrl ?: "default_photo_url",
        title = remoteAlbum.title,
        userId = remoteAlbum.userId,
        username = user?.username ?: "Unknown"
    )
}
