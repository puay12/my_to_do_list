package com.vsga2024.mytodolist.data.datasource.interfaces

interface AuthLocalDataSource {
    suspend fun saveToken(token: String)
    suspend fun getToken() : String?
}