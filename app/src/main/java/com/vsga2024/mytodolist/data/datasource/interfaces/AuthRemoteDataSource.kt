package com.vsga2024.mytodolist.data.datasource.interfaces

import com.vsga2024.mytodolist.data.model.body.LoginBody
import com.vsga2024.mytodolist.data.model.body.RegistBody
import com.vsga2024.mytodolist.data.model.response.LoginResponse
import com.vsga2024.mytodolist.data.model.response.RegistResponse

interface AuthRemoteDataSource {
    suspend fun userLogin(loginBody: LoginBody): LoginResponse
    suspend fun userRegist(registBody: RegistBody) : RegistResponse
}