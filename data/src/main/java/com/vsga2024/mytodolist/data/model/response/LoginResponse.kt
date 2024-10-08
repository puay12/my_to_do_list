package com.vsga2024.mytodolist.data.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LoginResponse(
    @field:SerializedName("token")
    val token: String,
)
