package com.example.helldivers.SettingGameDifficulty

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helldivers.Constantes
import com.example.helldivers.R
import com.example.helldivers.ui.theme.pixelifySansFamily

private var presenter = SettingGameDifficultyPresenter()
private val options = listOf(
    Constantes.TRIVIAL.description,
    Constantes.EASY.description,
    Constantes.MEDIUM.description,
    Constantes.CHALLENGING.description,
    Constantes.HARD.description,
    Constantes.EXTREME.description,
    Constantes.SUICIDE_MISSION.description,
    Constantes.IMPOSSIBLE.description,
    Constantes.HELLDIVE.description)
private var difficultSelected: MutableState<String> = mutableStateOf(options[0])

@Composable
fun SettingGameDifficultyView(
    navController: NavController,
    symbol: String?,
    nameSector: String?,
    percent: String?
) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (nameSector != null && symbol != null && percent != null)
            World(symbol, nameSector, percent)
        Column (
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            DifficultSelect()
            TextGame(difficultSelected.value, 16.sp, Color.White)
            SamplesByDifficult()
        }
    }
}

@Composable
fun World(symbol: String, nameSector: String, percent: String){
    Column (
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
    ){
        Row {
            Column (modifier = Modifier
                .padding(horizontal = 16.dp)
            ) { SymbolImage(symbol) }
            Column {
                TextGame(nameSector.uppercase(), 16.sp, presenter.GetColorTextBySymbol(symbol))
                TextGame("$symbol Control".uppercase(), 16.sp, presenter.GetColorTextBySymbol(symbol))
                TextGame("${percent.toFloat() * 100}% ${"Liberated".uppercase()}", 16.sp, color = Color.White)
            }
        }
        TextGame(
            presenter.BarOfPercentageLiberated(percent.toFloat()), 16.sp,
            presenter.GetColorTextBySymbol(symbol)
        )
    }
}

@Composable
fun DifficultSelect(){
    LazyRow (
        modifier = Modifier
            .width(350.dp)
            .verticalScroll(rememberScrollState())
    ) {
        items(options.size) { num ->
            val colorButton =
                if (num <= 2)
                    Color(140, 131, 115, 255)
                else if (num <= 5)
                    Color(166, 121, 89, 255)
                else if (num <= 8)
                    Color(117, 49, 41, 255)
                else
                    Color.Black
            Button(
                modifier = Modifier
                    .size(width = 200.dp, height = 40.dp)
                    .padding(horizontal = 5.dp),
                shape = RoundedCornerShape(2.dp),
                colors = ButtonDefaults.buttonColors(colorButton),
                onClick = {
                    difficultSelected.value = options[num]
                }
            ) {
                TextGame(options[num], 20.sp, Color.White)
            }
        }
    }
}

@Composable
fun SamplesByDifficult(){
    Row (
        verticalAlignment = Alignment.CenterVertically,
    ){
        TextGame("SAMPLES:", 16.sp, Color.White)
        val levelSamples = when (difficultSelected.value) {
            options[0], options[1], options[2] -> 1
            options[3], options[4], options[5] -> 2
            options[6], options[7], options[8] -> 3
            else -> "default"
        }
        when (levelSamples) {
            1 -> {
                ImageGame(painterResource(R.drawable.common_sample), ContentScale.None, 16.dp)
            }
            2 -> {
                ImageGame(painterResource(R.drawable.common_sample), ContentScale.None, 16.dp)
                ImageGame(painterResource(R.drawable.rare_sample), ContentScale.None, 16.dp)
            }
            3 -> {
                ImageGame(painterResource(R.drawable.common_sample), ContentScale.None, 16.dp)
                ImageGame(painterResource(R.drawable.rare_sample), ContentScale.None, 16.dp)
                ImageGame(painterResource(R.drawable.super_sample), ContentScale.None, 16.dp)
            }
        }
    }
}

@Composable
fun SymbolImage(symbol: String) {
    when (symbol) {
        Constantes.AUTOMATONS.description -> ImageGame(painterResource(R.drawable.symbol_automatons), ContentScale.Crop, 50.dp)
        Constantes.TERMINDS.description -> ImageGame(painterResource(R.drawable.symbol_terminds), ContentScale.Crop, 50.dp)
        Constantes.SUPER_EARTH.description -> ImageGame(painterResource(R.drawable.symbol_super_earth), ContentScale.Crop, 50.dp)
    }
}

@Composable
fun ImageGame(painterResource: Painter, contentScale: ContentScale, scale: Dp){
    Image(
        painter = painterResource,
        contentDescription = null,
        contentScale = contentScale,
        modifier = Modifier.size(scale)
    )
}

@Composable
fun TextGame(count: String, sp: TextUnit, color: Color){
    Text(text = count,
        fontFamily = pixelifySansFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = sp,
        color = color
    )
}