package com.toy.project.ctudy.model

import java.io.Serializable

/**
 * Login Form Data
 */
data class LoginData(
    val username: String,
    val password: String,
) : Serializable