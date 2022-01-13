package com.toy.project.ctudy.model

import java.io.Serializable

/**
 * 회원가입 Request Data
 */
data class SignUpData(
    val username: String,
    val password: String,
    val name: String,
) : Serializable