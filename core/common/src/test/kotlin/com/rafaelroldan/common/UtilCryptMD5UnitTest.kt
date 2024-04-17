package com.rafaelroldan.common

import org.junit.Test

import org.junit.Assert.*

class UtilCryptMD5UnitTest {
    @Test
    fun testGenerateHash() {

        val time: Long = 300
        val publicKey = "123456"
        val privateKey = "123456"

        assertEquals(UtilCryptMD5.generateHash(time, privateKey, publicKey),"c158d787f65cfe2f32900b7f56935252")
    }
}
