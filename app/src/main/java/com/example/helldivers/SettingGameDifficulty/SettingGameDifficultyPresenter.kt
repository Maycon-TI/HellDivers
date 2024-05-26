package com.example.helldivers.SettingGameDifficulty

import androidx.compose.ui.graphics.Color

private var model = SettingGameDifficultyModel()

class SettingGameDifficultyPresenter {
    fun BarOfPercentageLiberated(liberated: Float): String{
        return model.barOfPercentageLiberated(liberated)
    }

    fun GetColorTextBySymbol(symbol: String): Color {
        return model.ColorText(symbol)
    }
}