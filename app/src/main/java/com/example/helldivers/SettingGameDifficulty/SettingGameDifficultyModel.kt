package com.example.helldivers.SettingGameDifficulty

import androidx.compose.ui.graphics.Color
import com.example.helldivers.Constantes
import kotlin.math.round

class SettingGameDifficultyModel {

    fun barOfPercentageLiberated(liberated: Float): String {
        val percent = round(20 * liberated / 1f).toInt()
        return "▓".repeat(percent) + "░".repeat(20).substring(percent)
    }

    fun ColorText(symbol: String): Color {
        return when (symbol) {
            Constantes.AUTOMATONS.description -> Color(251, 67, 45, 255)
            Constantes.TERMINDS.description -> Color(255, 191, 0, 255)
            Constantes.SUPER_EARTH.description -> Color(133, 212, 223, 255)
            else -> Color.White
        }
    }
}