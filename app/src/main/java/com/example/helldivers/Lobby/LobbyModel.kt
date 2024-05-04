package com.example.helldivers.Lobby

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

class LobbyModel {
    var option1: String = "| SHIP MANAGEMENT |"

    fun alternateOption1(option: String){
        option1 = option
    }

    fun optionSelect(text: String): TextUnit {
        return if (text == option1)
            40.sp
        else
            25.sp
    }
}