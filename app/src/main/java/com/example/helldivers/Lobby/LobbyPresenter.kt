package com.example.helldivers.Lobby

import androidx.compose.ui.graphics.Color
import com.example.helldivers.entity.Sector

private val model = LobbyModel()

class LobbyPresenter{
    fun getList(): MutableList<Sector> {
        return model.getList()
    }

    fun addSectorOnList(symbol: String, nameSector: String, liberated: Float){
        model.addSectorOnList(symbol, nameSector, liberated)
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

