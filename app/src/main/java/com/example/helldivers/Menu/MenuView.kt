package com.example.helldivers.Menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.helldivers.Constantes
import com.example.helldivers.R
import com.example.helldivers.ui.theme.pixelifySansFamily

private val presenter = MenuPresenter()

@Composable
fun MenuView(
    navController: NavController
) {
    presenter.ActivateLaunchedEffect()

    Text(
        modifier = Modifier.padding(16.dp),
        fontFamily = pixelifySansFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 15.sp,
        color = Color.White,
        text = Constantes.NOTE.description
    )

    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth,
    )
    
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            ButtonTapToStart()
            TwoOptionButton(navController)
        }
        Version()
    }
}

@Composable
private fun ButtonTapToStart(){
    AnimatedVisibility(
        visible = presenter.getIsVisible(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Text(
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    presenter.setIsVisible(false)
                    presenter.setIsActive(false)
                },
            text = "tap to start",
            fontFamily = pixelifySansFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp,
            color = Color.White,
        )
    }
}

@Composable
fun TwoOptionButton(navController: NavController) {
    AnimatedVisibility(
        visible = !presenter.getIsActive(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ButtonClickable(
                text = "Continue",
                onClick = { presenter.goToGame(navController) }
            )
            ButtonClickable(
                text = "New Game",
                onClick = { presenter.goToLobbyView(navController) }
            )
        }
    }
}

@Composable
fun ButtonClickable(text: String, onClick: () -> Unit) {
    OutlinedButton(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(255, 255, 255, 255)),
        onClick = {onClick()}
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = text,
            fontFamily = pixelifySansFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp,
            color = Color.White,
        )
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

@Composable
@Preview
private fun PreviewGreeting() {
    MenuView(
        navController = rememberNavController()
    )
}