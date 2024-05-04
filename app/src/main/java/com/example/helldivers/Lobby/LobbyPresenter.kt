package com.example.helldivers.Lobby

import androidx.compose.ui.unit.TextUnit

val model = LobbyModel()

class LobbyPresenter(){

    fun getOption(): String{
        return model.option1
    }

    fun alternateOption1(option: String){
        model.alternateOption1(option)
    }

    fun optionSelect(text: String): TextUnit {
        return model.optionSelect(text)
    }

}