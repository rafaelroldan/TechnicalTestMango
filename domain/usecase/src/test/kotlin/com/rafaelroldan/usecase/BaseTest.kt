package com.rafaelroldan.usecase

import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before

abstract class BaseTest {

    @Before
    abstract fun setUp()

    @After
    open fun tearDown() {
        unmockkAll()
    }
}
