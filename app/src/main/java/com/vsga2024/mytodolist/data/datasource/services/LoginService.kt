package com.vsga2024.mytodolist.data.datasource.services

import com.vsga2024.mytodolist.data.model.body.LoginBody
import com.vsga2024.mytodolist.extensions.LOGIN_URL
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST(LOGIN_URL)
    suspend fun userLogin(@Body loginBody: LoginBody) : LoginResponse
}