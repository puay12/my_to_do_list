package com.vsga2024.mytodolist.data.datasource.interfaces

import com.vsga2024.mytodolist.data.model.body.LoginBody
import com.vsga2024.mytodolist.data.model.response.LoginResponse

interface AuthRemoteDataSource {
    suspend fun userLogin(loginBody: LoginBody): LoginResponse
}