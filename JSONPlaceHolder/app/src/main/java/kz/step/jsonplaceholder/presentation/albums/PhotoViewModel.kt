package kz.step.jsonplaceholder.presentation.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.step.jsonplaceholder.domain.repository.PhotoRepository

class PhotoViewModel(private val photoRepository: PhotoRepository) : ViewModel() {

    fun loadAlbums() {
        viewModelScope.launch {
            try {
                val albums = photoRepository.getAlbums()

            } catch (e: Exception) {
            }
        }
    }
}
