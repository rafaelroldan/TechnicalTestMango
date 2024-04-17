package com.rafaelroldan.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rafaelroldan.designsystem.theme.TechnicalTestMangoTheme
import com.rafaelroldan.designsystem.theme.ThemeConfig
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
                    delay(3000)
                    isReady = false
                }
            }
        }
    }
}
