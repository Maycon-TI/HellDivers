package com.example.helldivers.Lobby

import android.annotation.SuppressLint
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    Text(
        modifier = Modifier.padding(16.dp),
        fontFamily = pixelifySansFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 25.sp,
        color = Color.White,
        text = "NOTE: War is war"
    )
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
        itemCount(2, 0, painterResource(id = R.drawable.super_credits))
        itemCount(2, 0, painterResource(id = R.drawable.democracy_medals))
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
            itemCount(1, 0, painterResource(id = R.drawable.republic_points))
            itemCount(1, 0, painterResource(id = R.drawable.super_credits))
        }
        itemCount(0, 0, painterResource(id = R.drawable.democracy_medals))
        itemCount(0, 0, painterResource(id = R.drawable.common_sample))
        itemCount(0, 0, painterResource(id = R.drawable.rare_sample))
        itemCount(0, 0, painterResource(id = R.drawable.super_sample))
        Text(text = "Level 0",
            fontSize = 25.sp,
            fontFamily = pixelifySansFamily,
            fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun itemCount(type: Int, count: Int, painterResource: Painter){
    if (type == 0) {
        Column(
            modifier = Modifier.padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource,
                contentDescription = null,
                contentScale = ContentScale.None
            )
            Text(text = count.toString(),
                fontFamily = pixelifySansFamily,
                fontWeight = FontWeight.ExtraBold)
        }
    }else if (type == 1){
        Row (
            modifier = Modifier.padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = count.toString(),
                fontFamily = pixelifySansFamily,
                fontWeight = FontWeight.ExtraBold)
            Image(
                painter = painterResource,
                contentDescription = null,
                contentScale = ContentScale.None
            )
        }
    }else if (type == 2){
        Row (
            modifier = Modifier.padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource,
                contentDescription = null,
                contentScale = ContentScale.None
            )
            Text(text = count.toString(),
                fontFamily = pixelifySansFamily,
                fontWeight = FontWeight.ExtraBold)
        }
    }
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