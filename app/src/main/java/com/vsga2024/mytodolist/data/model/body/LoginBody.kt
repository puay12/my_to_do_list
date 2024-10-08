package com.vsga2024.mytodolist.data.model.body

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LoginBody(
    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("password")
    val password: String
)
