package com.vsga2024.mytodolist.data.datasource.services

import com.vsga2024.mytodolist.data.model.body.RegistBody
import com.vsga2024.mytodolist.extensions.REGIST_URL
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST(REGIST_URL)
    suspend fun userRegist(@Body registBody: RegistBody) : RegistResponse
}