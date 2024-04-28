package com.example.helldivers.Menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.example.helldivers.Screen
import kotlinx.coroutines.delay

class MenuModel{

    var isActive = mutableStateOf(true)
    var isVisible = mutableStateOf(true)

    fun changeValueActive(boolean: Boolean){
        isActive.value = boolean
    }

    fun changeValueVisible(boolean: Boolean){
        isVisible.value = boolean
    }

    @Composable
    fun ActivateLaunchedEffect(){
        LaunchedEffect(isActive.value) {
            while (isActive.value) {
                delay(500)
                isVisible.value = !isVisible.value
            }
        }
    }

    fun goToGame(navController: NavController) {
        navController.navigate(route = Screen.lobbyView.route)
    }

    fun goToLobby(navController: NavController) {
        navController.navigate(route = Screen.lobbyView.route)
    }



}