package com.example.helldivers

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.helldivers.LoadingGame.LoadingGameView
import com.example.helldivers.Lobby.LobbyView
import com.example.helldivers.Menu.MenuView
import com.example.helldivers.SettingGameDifficulty.SettingGameDifficultyView

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.loadingGameView.route
    ) {
        composable(route = Screen.loadingGameView.route) {
            LoadingGameView(navController = navController)
        }

        composable(route = Screen.menuView.route) {
            MenuView(navController = navController)
        }

        composable(route = Screen.lobbyView.route) {
            LobbyView(navController = navController)
        }

        composable(
            route = Screen.settingGameDifficultyView.route + "/{args}/{args2}/{args3}",
            arguments = listOf(navArgument("args") { type = NavType.StringType })
        ) {
            val args = it.arguments?.getString("args")
            val args2 = it.arguments?.getString("args2")
            val args3 = it.arguments?.getString("args3")
            SettingGameDifficultyView(navController = navController, symbol = args, nameSector = args2, percent = args3)
        }
    }
}