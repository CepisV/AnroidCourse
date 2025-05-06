package kz.step.jsonplaceholder.presentation.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.step.jsonplaceholder.domain.models.Album
import kz.step.jsonplaceholder.domain.use_case.GetAlbumsUseCase

class AlbumsViewModel(private val getAlbumsUseCase: GetAlbumsUseCase) : ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val albumsList = getAlbumsUseCase()
                if (albumsList.isEmpty()) {
                    _error.postValue("Нет альбомов для отображения")
                }
                _albums.postValue(albumsList)
            } catch (e: Exception) {
                _error.postValue("Не удалось загрузить альбомы: ${e.localizedMessage}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

}
