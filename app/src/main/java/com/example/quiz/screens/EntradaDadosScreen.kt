package com.example.quiz.screens

import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quiz.R
import com.example.quiz.components.CaixaDeEntrada

@Composable
fun DadosScreen (modifier: Modifier = Modifier, navController: NavController) {

    var nomeJogador by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
        .fillMaxSize()
        .background(
            color = colorResource(R.color.blue_white)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(80.dp),
            painter = painterResource(
                R.drawable.quiz_icon
            ),
            contentDescription = "logo quiz"
        )

        Spacer(modifier = Modifier.height(50.dp))


        CaixaDeEntrada(
            modifier = Modifier,
            label = "Nome do Jogador",
            placeholder = "Digite seu nome de jogador",
            keyboardType = KeyboardType.Text,
            value = nomeJogador,
            atualizarValor = { novoNome -> nomeJogador = novoNome },
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                if(nomeJogador.isNotBlank()) {
                    navController.navigate("quiz/$nomeJogador")
                }
            },
            modifier = Modifier
                .height(80.dp)
                .width(250.dp)
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.yellow_white)),
            border = BorderStroke(
                width = 2.dp,
                color = colorResource(R.color.black)
            )
        ) {
            Text(
                text = "JOGAR!",
                color = colorResource(R.color.black),
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }

    }
}