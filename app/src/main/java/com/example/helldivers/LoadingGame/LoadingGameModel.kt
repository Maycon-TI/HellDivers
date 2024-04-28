package com.example.helldivers.LoadingGame

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.navigation.NavController
import com.example.helldivers.Screen
import kotlinx.coroutines.delay
import kotlin.math.round

class LoadingGameModel {

    var downloadProgress = mutableFloatStateOf(0.1f)
    private var delayProgressBar = 500L

    @Composable
    fun ActivateLaunchedEffect(navController: NavController) {
        LaunchedEffect(downloadProgress.floatValue) {
            while (downloadProgress.floatValue <= 1f) {
                delay(delayProgressBar)
                downloadProgress.floatValue += 0.1f
            }
            if (downloadProgress.floatValue >= 1f) {
                navController.navigate(route = Screen.menuView.route) {
                    popUpTo(Screen.loadingGameView.route) { inclusive = true }
                }
            }
        }
    }

    fun calculateDownload(): Int {
        return round(downloadProgress.floatValue * 100).toInt()
    }
}