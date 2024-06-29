package com.example.zigzagnotes.util

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName


data class ErrorResponse (
val errorCode: Int,
val errorMessage: String
)