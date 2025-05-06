package kz.step.jsonplaceholder.presentation.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.step.jsonplaceholder.domain.use_case.GetPostsUseCase
import kz.step.jsonplaceholder.domain.models.Post

class PostsViewModel(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {

    private val _postsLiveData: MutableLiveData<List<Post>> = MutableLiveData()
    val postsLiveData: LiveData<List<Post>> = _postsLiveData

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            val posts = getPostsUseCase()
            _postsLiveData.postValue(posts)
        }
    }

}