package com.vsga2024.mytodolist.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.vsga2024.mytodolist.data.datasource.interfaces.AuthLocalDataSource
import com.vsga2024.mytodolist.data.datasource.interfaces.AuthRemoteDataSource
import com.vsga2024.mytodolist.data.datasource.local.AuthLocalDataSourceImpl
import com.vsga2024.mytodolist.data.model.body.LoginBody
import com.vsga2024.mytodolist.data.model.response.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : ViewModel() {
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras

                return AuthViewModel(
                    (application as AuthViewModel).authLocalDataSource,
                    (application as AuthViewModel).authRemoteDataSource
                ) as T
            }
        }
    }

    private val _loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val _error: MutableLiveData<String> = MutableLiveData<String>()
    private val _success: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun userLogin(username: String, password: String) {
        val loginBody = LoginBody(username, password)

        viewModelScope.launch {
            try {
                _loading.value = true
                val res: LoginResponse = authRemoteDataSource.userLogin(loginBody)

                if (res.errorMessage == null) {
                    authLocalDataSource.saveToken(res.data?.token!!)
                    _success.value = true
                } else {
                    _error.value = res.errorMessage
                    _success.value = false
                }
            } catch (error: Throwable) {
                _error.value = error.message
            }
            _loading.value = false
        }
    }

    fun getError() : LiveData<String> {
        return _error
    }

    fun getLoading() : LiveData<Boolean> {
        return _loading
    }

    fun getSuccess() : LiveData<Boolean> {
        return _success
    }
}