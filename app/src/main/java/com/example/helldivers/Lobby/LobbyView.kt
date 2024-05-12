package com.example.helldivers.Lobby

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helldivers.R
import com.example.helldivers.ui.theme.pixelifySansFamily

private var presenter = LobbyPresenter()

//private val optionSelected1 = MutableStateFlow("| GALACTIC WAR |")

var optionSelected1 = mutableStateOf("| GALACTIC WAR |")
var optionSelected2 = mutableStateOf("| GALACTIC WAR |")


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LobbyView(
    navController: NavController
) {

    Column {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .background(Color.Gray),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("| GALACTIC WAR |", "| SHIP MANAGEMENT |", "| ARMORY |", "| ACQUISITIONS |", "| ORDERS |").forEach { text ->
                TextClickable(text = text, optionSelected1)
            }
        }
        Row {
            when (optionSelected1.value) {
                "| GALACTIC WAR |" -> { GalacticWar() }
                "| SHIP MANAGEMENT |" -> { ShipManagement() }
                "| ARMORY |" -> { Armory() }
                "| ACQUISITIONS |" -> { Acquisitions() }
                "| ORDERS |" -> { Orders() }
            }
        }
    }
    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ){
        Version()
    }
}

@Composable
fun GalacticWar(){
    Column {
        Row {
            Column(modifier = Modifier.padding(horizontal = 16.dp)){
                TextGame("Enemies killed", 25.sp, Color.White)
                TextGame("0", 25.sp, Color.White)
                TextGame("Bullets Fired", 25.sp, Color.White)
                TextGame("0", 25.sp, Color.White)
            }
            
            Column {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    TextGame("- = Galactic War = -", 25.sp, Color.White)
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ){
                    items(presenter.getList().size) { index ->
                        val sector = presenter.getSector(index)
                        WorldsWarMap(sector)
                    }
                }
            }
        }
    }
}

@Composable
fun WorldsWarMap(sector: Sector) {
    Card (
        modifier = Modifier
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
        ),
        border = BorderStroke(1.dp, Color.Yellow),
        ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (modifier = Modifier.fillMaxWidth()){
                SymbolImage(sector.symbol)
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                ){
                    TextGame(sector.nameSector, 16.sp, presenter.GetColorTextBySymbol(sector.symbol))
                }
            }
            TextGame(presenter.BarOfPercentageLiberated(sector.liberated), 16.sp, presenter.GetColorTextBySymbol(sector.symbol))
            TextGame("${sector.liberated * 100}% Liberated", 16.sp, Color.White)
        }
    }
}

@Composable
fun SymbolImage(symbol: String) {
    when (symbol) {
        "Automatons" -> ImageGame(painterResource(id = R.drawable.symbol_automatons))
        "Terminds" -> ImageGame(painterResource(id = R.drawable.symbol_terminds))
        "Super Earth" -> ImageGame(painterResource(id = R.drawable.symbol_super_earth))
    }
}

@Composable
fun ShipManagement(){
    ListButton(listOf("| DESTROYER |", "| STRATAGEMS |", "| SHIP MODULE |"), optionSelected2)
    Money()
}

@Composable
fun Armory(){
    ListButton(listOf("| WEAPONRY |", "| ARMORY |", "| CHARACTER |", "| BOOSTER |", "| CAREER |"), optionSelected2)
}

@Composable
fun Acquisitions(){
    ListButton(listOf("| WARBONDS |", "| SUPERSTORE |", "| SUPER CREDITS |"), optionSelected2)
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ){
        ItemCount(2, 0, painterResource(id = R.drawable.super_credits))
        ItemCount(2, 0, painterResource(id = R.drawable.democracy_medals))
    }
}

@Composable
fun Orders(){
    ListButton(listOf("| GAME |", "| SOCIAL |", "| OPTIONS |", "| PLAY TUTORIAL |", "| CREDITS |", "| QUIT GAME |"), optionSelected2)
    Money()
}

@Composable
fun ListButton(list: List<String>, optionSelected2: MutableState<String>){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        list.forEach { text ->
            TextClickable(text, optionSelected2)
        }
    }
}

@Composable
fun TextClickable(text: String, optionSelected1: MutableState<String>) {
    var textSize by remember { mutableStateOf(25.sp) }

    LaunchedEffect(optionSelected1.value) {
        textSize = if (optionSelected1.value == text) 40.sp else 25.sp
    }

    Text(
        fontFamily = pixelifySansFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = textSize,
        text = text,
        modifier = Modifier
            .padding(5.dp)
            .clickable { optionSelected1.value = text }
    )
}

@Composable
fun Money(){
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column{
            ItemCount(1, 0, painterResource(id = R.drawable.republic_points))
            ItemCount(1, 0, painterResource(id = R.drawable.super_credits))
        }
        ItemCount(0, 0, painterResource(id = R.drawable.democracy_medals))
        ItemCount(0, 0, painterResource(id = R.drawable.common_sample))
        ItemCount(0, 0, painterResource(id = R.drawable.rare_sample))
        ItemCount(0, 0, painterResource(id = R.drawable.super_sample))
        Text(text = "Level 0",
            fontSize = 25.sp,
            fontFamily = pixelifySansFamily,
            fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun ItemCount(type: Int, count: Int, painterResource: Painter){
    when (type) {
        0 -> {
            Column(
                modifier = Modifier.padding(horizontal = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageGame(painterResource = painterResource)
                TextGame(count = count.toString(), sp = 16.sp, color = Color.White)
            }
        }
        1 -> {
            Row (
                modifier = Modifier.padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextGame(count = count.toString(), sp = 16.sp, color = Color.White)
                ImageGame(painterResource = painterResource)
            }
        }
        2 -> {
            Row (
                modifier = Modifier.padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageGame(painterResource = painterResource)
                TextGame(count = count.toString(), sp = 16.sp, color = Color.White)
            }
        }
    }
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

@Composable
fun ImageGame(painterResource: Painter){
    Image(
        painter = painterResource,
        contentDescription = null,
        contentScale = ContentScale.None
    )
}

@Composable
private fun Version(){
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End){
        Text(
            text = "0.0.0v",
            fontFamily = pixelifySansFamily,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}