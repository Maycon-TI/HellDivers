package com.example.helldivers.LoadingGame

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlin.math.round

private val model = LoadingGameModel();

class LoadingGamePresenter{

    fun getDownloadProgress(): Float {
        return model.downloadProgress.floatValue
    }

    @Composable
    fun ActivateLaunchedEffect(navController: NavController){
        model.ActivateLaunchedEffect(navController = navController)
    }

    fun calculateDownload(): Int {
        return model.calculateDownload()
    }

}