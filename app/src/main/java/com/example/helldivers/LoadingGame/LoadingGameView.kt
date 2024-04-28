package com.example.helldivers.LoadingGame

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.helldivers.R
import com.example.helldivers.ui.theme.pixelifySansFamily

private val presenter = LoadingGamePresenter()

@Composable
fun LoadingGameView(
    navController: NavController
) {
    presenter.ActivateLaunchedEffect(navController)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.None,
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fontFamily = pixelifySansFamily,
            fontWeight = FontWeight.ExtraBold,
            text = "${presenter.calculateDownload()}%"
        )
        val animatedDownloadProgress by animateFloatAsState(
            targetValue = presenter.getDownloadProgress(), label = ""
        )
        LinearProgressIndicator(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth(),
            progress = animatedDownloadProgress,
            color = Color(91, 110, 225, 255)
        )
    }
}

@Composable
@Preview
private fun PreviewHomeView() {
    LoadingGameView(
        navController = rememberNavController()
    )
}