package com.vsga2024.mytodolist.data.datasource.remote

import com.vsga2024.mytodolist.data.datasource.interfaces.AuthRemoteDataSource
import com.vsga2024.mytodolist.data.datasource.services.LoginService
import com.vsga2024.mytodolist.data.datasource.services.RegisterService
import com.vsga2024.mytodolist.data.model.body.LoginBody
import com.vsga2024.mytodolist.data.model.body.RegistBody

class AuthRemoteDataSourceImpl(
    private val loginService: LoginService,
    private val registerService: RegisterService
) : AuthRemoteDataSource {
    override suspend fun userLogin(loginBody: LoginBody): LoginResponse {
        return loginService.userLogin(loginBody)
    }

    override suspend fun userRegist(registBody: RegistBody): RegistResponse {
        return registerService.userRegist(registBody)
    }

}