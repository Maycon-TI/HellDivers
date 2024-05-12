package com.example.helldivers.Lobby

import androidx.compose.ui.graphics.Color
import kotlin.math.round

val listSectors = mutableListOf(
    Sector("Super Earth", "Sol System", 1f),
    Sector("Super Earth", "Akira Sector", 1f),
    Sector("Super Earth", "Altus Sector", 1f),
    Sector("Automatons", "Andromeda Sector", 0f),
    Sector("Super Earth", "Arturion Sector", 1f),
    Sector("Super Earth", "Barnard Sector", 1f),
    Sector("Super Earth", "Borgus Sector", 1f),
    Sector("Super Earth", "Cancri Sector", 1f),
    Sector("Super Earth", "Cantolus Sector", 1f),
    Sector("Super Earth", "Celeste Sector", 1f),
    Sector("Terminds", "Draco Sector", 0f),
    Sector("Super Earth", "Falstaff Sector", 1f),
    Sector("Super Earth", "Farsight Sector", 1f),
    Sector("Super Earth", "Ferris Sector", 1f),
    Sector("Super Earth", "Gallux Sector", 1f),
    Sector("Super Earth", "Gellert Sector", 1f),
    Sector("Super Earth", "Gothmar Sector", 1f),
    Sector("Super Earth", "Guang Sector", 1f),
    Sector("Super Earth", "Hanzo Sector", 1f),
    Sector("Super Earth", "Hawking Sector", 1f),
    Sector("Automatons", "Hydra Sector", 0f),
    Sector("Super Earth", "Idun Sector", 1f),
    Sector("Super Earth", "Iptus Sector", 1f),
    Sector("Terminds", "Jin Xi Sector", 0f),
    Sector("Super Earth", "Kelvin Sector", 1f),
    Sector("Super Earth", "Korpus Sector", 1f),
    Sector("Terminds", "L'estrade Sector", 1f),
    Sector("Automatons", "Lacaille Sector", 0f),
    Sector("Super Earth", "Leo Sector", 0f),
    Sector("Super Earth", "Marspira Sector", 1f),
    Sector("Super Earth", "Meridian Sector", 1f),
    Sector("Terminds", "Mirin Sector", 0f),
    Sector("Super Earth", "Morgon Sector", 1f),
    Sector("Super Earth", "Omega Sector", 1f),
    Sector("Super Earth", "Orion Sector", 1f),
    Sector("Super Earth", "Quintus Sector", 1f),
    Sector("Super Earth", "Rictus Sector", 1f),
    Sector("Super Earth", "Rigel Sector", 1f),
    Sector("Super Earth", "Sagan Sector", 1f),
    Sector("Super Earth", "Saleria Sector", 1f),
    Sector("Super Earth", "Severin Sector", 1f),
    Sector("Terminds", "Sten Sector", 0f),
    Sector("Super Earth", "Talus Sector", 1f),
    Sector("Super Earth", "Tanis Sector", 1f),
    Sector("Super Earth", "Tarragon Sector", 1f),
    Sector("Super Earth", "Theseus Sector", 1f),
    Sector("Automatons", "Trigon Sector", 0f),
    Sector("Terminds", "Umlaut Sector", 0f),
    Sector("Super Earth", "Ursa Sector", 1f),
    Sector("Automatons", "Valdis Sector", 0f),
    Sector("Super Earth", "Xi Tauri Sector", 1f),
    Sector("Super Earth", "Xzar Sector", 1f),
    Sector("Automatons", "Ymir Sector", 0f)
)

class Sector(
    var symbol: String,
    var nameSector: String,
    var liberated: Float

)

class User(
    var commonSample: Int,
    var rareSample: Int,
    var superSample: Int,
    var superCredits: Int,
    var republicPoints: Int,
    var democracyMedals: Int,
    var level: Int,
    var xp: Float,
    var maxXPtoUP: Float
)

class LobbyModel {
    fun getList(): MutableList<Sector> {
        return listSectors
    }

    fun getSector(index: Int): Sector {
        return listSectors[index]
    }

    fun ColorText(symbol: String): Color {
        return when (symbol) {
            "Automatons" -> Color(251, 67, 45, 255)
            "Terminds" -> Color(255, 191, 0, 255)
            "Super Earth" -> Color(133, 212, 223, 255)
            else -> Color.White
        }
    }

    fun barOfPercentageLiberated(liberated: Float): String {
        val percent = round(10 * liberated / 1f).toInt()
        val text = "/".repeat(percent) + "-".repeat(10).substring(percent)
        return "[$text]"

    }
}