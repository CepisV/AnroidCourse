package kz.step.jsonplaceholder.presentation.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.step.jsonplaceholder.domain.models.Comment
import kz.step.jsonplaceholder.domain.use_case.GetPostCommentsUseCase

class CommentsViewModel(
    private val getPostCommentsUseCase: GetPostCommentsUseCase,
    postId: Int
) : ViewModel() {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> = _comments

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchComments(postId)
    }

    private fun fetchComments(postId: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val commentsList = getPostCommentsUseCase(postId)
                _comments.postValue(commentsList)
                _error.postValue(null)
            } catch (e: Exception) {
                _error.postValue("Не удалось загрузить комментарии: ${e.localizedMessage}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}

