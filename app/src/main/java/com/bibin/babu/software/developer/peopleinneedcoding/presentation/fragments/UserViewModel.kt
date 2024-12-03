package com.bibin.babu.software.developer.peopleinneedcoding.presentation.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.UserModel
import com.bibin.babu.software.developer.peopleinneedcoding.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {
    private lateinit var job: Job
    private val mUserMutableFlow = MutableStateFlow<List<UserModel>>(emptyList())
    val mUserList: StateFlow<List<UserModel>> get() = mUserMutableFlow.asStateFlow()
    val userName = MutableLiveData("")
    val userEmail = MutableLiveData("")
    val nameError = MutableLiveData<String?>()
    val emailError = MutableLiveData<String?>()

    init {
        mGetAllUserList()
    }

    private fun mGetAllUserList() {
        job = viewModelScope.launch {
            repository.getAllUserDb().flowOn(Dispatchers.IO).catch {
                throw Exception(it)
            }.collect {
                mUserMutableFlow.value = it
                Log.d("mAddUser", "mAddUser: $it")
            }
        }
    }

    fun mAddUser() {


        val name = userName.value?.trim() ?: ""
        val email = userEmail.value?.trim() ?: ""
        nameError.value = null
        emailError.value = null



        if (name.isNotEmpty() && email.isNotEmpty()) {
            job = viewModelScope.launch {
                repository.insertUser(UserModel(name = name, email = email))
            }
            userName.value = ""
            userEmail.value = ""
        }else{

            if (name.isEmpty()) {
                nameError.value = "Name cannot be empty"
            }

            if (email.isEmpty()) {
                emailError.value = "Email cannot be empty"
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

}