package com.rafaelroldan.common

import com.rafaelroldan.dto.AvatarImageDto
import com.rafaelroldan.dto.imageUrl
import org.junit.Test

import org.junit.Assert.*

class AvatarImageUnitTest {

    private val avatar = AvatarImageDto( "jpg", "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"

    )
    @Test
    fun testGenerateHash() {
        assertEquals(avatar.imageUrl(),"http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg")
    }
}
