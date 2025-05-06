package kz.step.jsonplaceholder.presentation.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kz.step.jsonplaceholder.domain.use_case.GetUsersUseCase
import kz.step.jsonplaceholder.domain.models.User
import kotlinx.coroutines.launch

class UsersFragmentViewmodel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    private val _usersLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> get() = _usersLiveData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getUsers() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val users = getUsersUseCase.execute()
                _usersLiveData.value = users
            } catch (e: Exception) {
                _usersLiveData.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
