package com.example.helldivers.Menu

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

private val model = MenuModel()

class MenuPresenter{

    @Composable
    fun ActivateLaunchedEffect(){
        model.ActivateLaunchedEffect()
    }

    fun getIsActive(): Boolean {
        return model.isActive.value
    }

    fun setIsActive(boolean: Boolean) {
        model.changeValueActive(boolean)
    }

    fun getIsVisible(): Boolean {
        return model.isVisible.value
    }

    fun setIsVisible(boolean: Boolean) {
        model.changeValueVisible(boolean)
    }

    fun goToLobbyView(navController: NavController) {
        model.goToLobby(navController)
    }

    fun goToGame(navController: NavController) {
        model.goToGame(navController)
    }
}