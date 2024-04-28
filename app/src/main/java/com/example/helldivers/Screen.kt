package com.example.helldivers

sealed class Screen(val route: String) {
    data object loadingGameView: Screen(route = "loading_game_view")
    data object menuView: Screen(route = "menu_view")
    data object lobbyView: Screen(route = "lobby_view")
}