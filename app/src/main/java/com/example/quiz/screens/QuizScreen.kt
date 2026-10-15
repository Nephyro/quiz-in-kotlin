package com.example.quiz.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quiz.components.CardPergunta
import com.example.quiz.listaDePerguntas
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    nomeJogador: String
) {
    val perguntasEmbaralhadas = remember { listaDePerguntas.shuffled().take(3) }

    var indiceAtual by remember { mutableStateOf(0) }
    var pontos by remember { mutableStateOf(0) }
    var quizFinalizado by remember { mutableStateOf(false) }

    // Armazena temporariamente a opção clicada para pintar os botões
    var opcaoSelecionadaAtual by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(com.example.quiz.R.color.pink_white)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!quizFinalizado && indiceAtual < perguntasEmbaralhadas.size) {
            val perguntaAtual = perguntasEmbaralhadas[indiceAtual]

            Image(
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(com.example.quiz.R.drawable.quiz_icon),
                contentDescription = "logo quiz"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color(0xFF62DB96)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Pergunta ${indiceAtual + 1} de ${perguntasEmbaralhadas.size}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }

            CardPergunta(
                pergunta = perguntaAtual.titulo,
                opcoes = perguntaAtual.opcoesEmbaralhadas,
                respostaCorreta = perguntaAtual.respostaCorreta,
                opcaoSelecionada = opcaoSelecionadaAtual,
                onOpcaoSelecionada = { opcaoEscolhida ->
                    // 1. Marca a opção escolhida para pintar o botão de verde/vermelho
                    opcaoSelecionadaAtual = opcaoEscolhida

                    // 2. Conta os pontos se acertou
                    if (opcaoEscolhida == perguntaAtual.respostaCorreta) {
                        pontos++
                    }

                    // 3. Aguarda 1 segundo antes de mudar de pergunta para o usuário ver a cor
                    coroutineScope.launch {
                        delay(1000L) // 1 segundo de pausa
                        opcaoSelecionadaAtual = null // Reseta a seleção para a próxima pergunta

                        if (indiceAtual < perguntasEmbaralhadas.size - 1) {
                            indiceAtual++
                        } else {
                            quizFinalizado = true
                        }
                    }
                }
            )
        } else {
            // Sua tela de resultado final (mantida igual)
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(70.dp),
                    painter = painterResource(com.example.quiz.R.drawable.quiz_icon),
                    contentDescription = "logo quiz"
                )

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(color = colorResource(com.example.quiz.R.color.blue_white)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(250.dp)
                            .height(60.dp)
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .align(Alignment.CenterHorizontally)
                            .clip(RoundedCornerShape(16.dp))
                            .background(color = Color(0xFF62DB96)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Bom trabalho, $nomeJogador!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Você acertou $pontos de ${perguntasEmbaralhadas.size} perguntas",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}