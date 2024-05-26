package com.example.helldivers.Lobby

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helldivers.Constantes
import com.example.helldivers.entity.Sector
import com.example.helldivers.R
import com.example.helldivers.Screen
import com.example.helldivers.ui.theme.pixelifySansFamily

private var presenter = LobbyPresenter()

var optionSelected1 = mutableStateOf(Constantes.ORDERS.description)
var optionSelected2 = mutableStateOf(Constantes.CREDITS.description)

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
            listOf(
                Constantes.GALACTIC_WAR.description,
                Constantes.SHIP_MANAGEMENT.description,
                Constantes.ARMORY.description,
                Constantes.ACQUISITIONS.description,
                Constantes.ORDERS.description
            ).forEach { text ->
                TextClickable(text = text, optionSelected1)
            }
        }
        Row {
            when (optionSelected1.value) {
                Constantes.GALACTIC_WAR.description -> { GalacticWar(navController) }
                Constantes.SHIP_MANAGEMENT.description -> { ShipManagement() }
                Constantes.ARMORY.description -> { Armory() }
                Constantes.ACQUISITIONS.description -> { Acquisitions() }
                Constantes.ORDERS.description -> { Orders() }
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
fun GalacticWar(navController: NavController) {
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
                        WorldsWarMap(sector, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun WorldsWarMap(sector: Sector, navController: NavController) {
    val context = LocalContext.current
    Card (
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                if (sector.liberated >= 1.0f)
                    Toast
                        .makeText(
                            context,
                            "Completed",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                else {
                    navController.navigate(
                        route = Screen.settingGameDifficultyView.route +
                                "/${sector.symbol}" +
                                "/${sector.nameSector}" +
                                "/${sector.liberated}"
                    )
                }
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
        ),
        border = BorderStroke(1.dp, Color.Yellow)

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
fun ShipManagement(){
    ListButton(listOf(
        Constantes.DESTROYER.description,
        Constantes.STRATAGEMS.description,
        Constantes.SHIP_MODULE.description), optionSelected2)
    Money()
}

@Composable
fun Armory(){
    ListButton(listOf(
        Constantes.WEAPONRY.description,
        Constantes.ARMORY.description,
        Constantes.CHARACTER.description,
        Constantes.BOOSTER.description,
        Constantes.CAREER.description), optionSelected2)
}

@Composable
fun Acquisitions(){
    ListButton(listOf(
        Constantes.WARBONDS.description,
        Constantes.SUPERSTORE.description,
        Constantes.SUPER_CREDITS.description), optionSelected2)
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
    ListButton(listOf(
        Constantes.GAME.description,
        Constantes.OPTIONS.description,
        Constantes.PLAY_TUTORIAL.description,
        Constantes.CREDITS.description,
        Constantes.QUIT_GAME.description), optionSelected2)
    Column {
        Money()
        when (optionSelected2.value){
            Constantes.CREDITS.description -> {
                Column (modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    TextGame(count = "\n░▒▓ Créditos do Jogo ▓▒░\n", sp = 20.sp, color = Color.White)
                    TextGame(count = "- Desenvolvimento -\n", sp = 20.sp, color = Color.White)
                    TextGame(count = "Designers de Jogo: [Puudimmmm]\n" +
                            "Programadores: [Puudimmmm, Siegmayer]\n" +
                            "Artistas: [Puudimmmm]\n", sp = 20.sp, color = Color.White)
                    TextGame(count = "░▒▓ GitHub ▓▒░\n", sp = 20.sp, color = Color.White)
                    TextGame(
                        count = "Puudimmmmm (Maycon-TI)\n" +
                                "Siegmayer (Douglas-TI)\n", sp = 20.sp, color = Color.White)
                }
            }
        }
    }
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
fun TextClickable(text: String, optionSelected: MutableState<String>) {
    var textSize by remember { mutableStateOf(25.sp) }

    LaunchedEffect(optionSelected.value) {
        textSize = if (optionSelected.value == text) 40.sp else 25.sp
    }

    Text(
        fontFamily = pixelifySansFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = textSize,
        text = text,
        modifier = Modifier
            .padding(5.dp)
            .clickable { optionSelected.value = text }
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
fun SymbolImage(symbol: String) {
    when (symbol) {
        Constantes.AUTOMATONS.description -> ImageGame(painterResource(id = R.drawable.symbol_automatons))
        Constantes.TERMINDS.description -> ImageGame(painterResource(id = R.drawable.symbol_terminds))
        Constantes.SUPER_EARTH.description -> ImageGame(painterResource(id = R.drawable.symbol_super_earth))
    }
}

@Composable
private fun Version(){
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End){
        Text(
            text = Constantes.VERSION.description,
            fontFamily = pixelifySansFamily,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}