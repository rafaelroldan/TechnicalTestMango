package com.rafaelroldan.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
open class BaseTestCoroutine(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : BaseTest() {

    @Before
    override fun setUp() {
        Dispatchers.setMain(testDispatcher)
        DispatchersConfig.ioDispatcher = testDispatcher
    }

    @After
    override fun tearDown() {
        super.tearDown()
        Dispatchers.resetMain()
    }
}

suspend fun <T> ioLaunch(block: suspend CoroutineScope.() -> T): T =
    withContext(DispatchersConfig.ioDispatcher) { block() }

object DispatchersConfig {

    var ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    var uiDispatcher: CoroutineDispatcher = Dispatchers.Main
}
