package com.example.helldivers

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.helldivers.LoadingGame.LoadingGameView
import com.example.helldivers.Lobby.LobbyView
import com.example.helldivers.Menu.MenuView

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.loadingGameView.route
    ){
        composable(
            route = Screen.loadingGameView.route
        ){
            LoadingGameView(navController = navController)
        }

        composable(
            route = Screen.menuView.route
        ){
            MenuView(navController = navController)
        }

        composable(
            route = Screen.lobbyView.route
        ){
            LobbyView(navController = navController)
        }
    }
}