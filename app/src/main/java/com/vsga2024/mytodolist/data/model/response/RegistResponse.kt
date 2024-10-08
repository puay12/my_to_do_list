package com.vsga2024.mytodolist.data.model.response

import com.google.gson.annotations.SerializedName

data class RegistResponse(

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("errorMessage")
	val errorMessage: Any? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)
