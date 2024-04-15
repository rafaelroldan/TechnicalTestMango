package com.rafaelroldan.ui

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.rafaelroldan.designsystem.components.CharacterRow
import com.rafaelroldan.ui.navigation.Navigation
import com.rafaelroldan.designsystem.theme.TechnicalTestMangoTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private var isReady : Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { isReady }

        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            Navigation()

            LaunchedEffect(Unit){
                delay(3000)
                isReady = false
            }


        }
    }
}