package com.rafaelroldan.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rafaelroldan.common.Constants
import com.rafaelroldan.designsystem.theme.TechnicalTestMangoTheme
import com.rafaelroldan.ui.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var isReady : Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { isReady }

        super.onCreate(savedInstanceState)
        setContent {
            TechnicalTestMangoTheme {
                Navigation()
                LaunchedEffect(Unit){
                    delay(Constants.DELAY_CONST)
                    isReady = false
                }
            }
        }
    }
}
