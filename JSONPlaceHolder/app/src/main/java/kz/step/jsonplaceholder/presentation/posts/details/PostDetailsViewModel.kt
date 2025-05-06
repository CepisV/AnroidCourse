package kz.step.jsonplaceholder.presentation.posts.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.step.jsonplaceholder.domain.models.Comment
import kz.step.jsonplaceholder.domain.models.Post
import kz.step.jsonplaceholder.domain.models.User
import kz.step.jsonplaceholder.domain.use_case.GetPostByIdUseCase
import kz.step.jsonplaceholder.domain.use_case.GetPostCommentsUseCase
import kz.step.jsonplaceholder.domain.use_case.GetUserUseCase

private const val COMMENTS_LIMIT = 5

class PostDetailsViewModel(
    private val postId: Int,
    private val getPostByIdUseCase: GetPostByIdUseCase,
    private val getPostCommentsUseCase: GetPostCommentsUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post> = _post

    private val _author = MutableLiveData<User>()
    val author: LiveData<User> = _author

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> = _comments

    init {
        loadPostDetails()
        loadComments()
    }

    private fun loadPostDetails() {
        viewModelScope.launch {
            try {
                val post = getPostByIdUseCase(postId)
                _post.postValue(post)
                loadAuthor(post.userId)
            } catch (e: Exception) {
            }
        }
    }

    private fun loadAuthor(userId: Int) {
        viewModelScope.launch {
            try {
                val user = getUserUseCase(userId)
                _author.postValue(user)
            } catch (e: Exception) {
            }
        }
    }

    private fun loadComments() {
        viewModelScope.launch {
            try {
                val comments = getPostCommentsUseCase(postId, limitBy = COMMENTS_LIMIT)
                _comments.postValue(comments)
            } catch (e: Exception) {
            }
        }
    }
}
