package com.example.helldivers.Lobby

import androidx.compose.ui.graphics.Color

private val model = LobbyModel()

class LobbyPresenter(){
    fun getList(): MutableList<Sector> {
        return model.getList()
    }

    fun GetColorTextBySymbol(symbol: String): Color {
        return model.ColorText(symbol)
    }

    fun BarOfPercentageLiberated(liberated: Float): String{
        return model.barOfPercentageLiberated(liberated)
    }

    fun getSector(index: Int): Sector {
        return model.getSector(index)
    }
}

