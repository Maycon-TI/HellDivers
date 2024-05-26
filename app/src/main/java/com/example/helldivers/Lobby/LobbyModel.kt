package com.example.helldivers.Lobby

import androidx.compose.ui.graphics.Color
import com.example.helldivers.Constantes
import com.example.helldivers.entity.Sector
import kotlin.math.round

val listSectors = mutableListOf(
    Sector(Constantes.SUPER_EARTH.description, "Sol System", 1f),
    Sector(Constantes.SUPER_EARTH.description, "Akira Sector", 1f),
    Sector(Constantes.TERMINDS.description, "Altus Sector", 0f),
    Sector(Constantes.AUTOMATONS.description, "Andromeda Sector", 0f),

    Sector(Constantes.AUTOMATONS.description, "Test Sector", 0.5f),
    Sector(Constantes.TERMINDS.description, "Test2 Sector", 0.25f),
    Sector(Constantes.TERMINDS.description, "Test3 Sector", 0.75f),
)

class LobbyModel {
    fun getList(): MutableList<Sector> {
        return listSectors
    }

    fun getSector(index: Int): Sector {
        return listSectors[index]
    }

    fun addSectorOnList(symbol: String, nameSector: String, liberated: Float){
        listSectors.add(Sector(symbol, nameSector, liberated))
    }

    fun ColorText(symbol: String): Color {
        return when (symbol) {
            Constantes.AUTOMATONS.description -> Color(251, 67, 45, 255)
            Constantes.TERMINDS.description -> Color(255, 191, 0, 255)
            Constantes.SUPER_EARTH.description -> Color(133, 212, 223, 255)
            else -> Color.White
        }
    }

    fun barOfPercentageLiberated(liberated: Float): String {
        val percent = round(10 * liberated / 1f).toInt()
        return "▓".repeat(percent) + "░".repeat(10).substring(percent)

    }
}