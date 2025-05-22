package com.nextgen.focusfight

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform