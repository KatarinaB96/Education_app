package com.tutorials.petersonsproject.domain.hierarchy

data class SessionCookie(
    val id: String,
    val type: String,
    val d2lSessionVal: D2lSessionVal,
    val d2lSecureSessionVal: D2lSecureSessionVal
)