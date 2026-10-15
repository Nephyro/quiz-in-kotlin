package com.example.quiz.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Composable
//fun CardPergunta(
//    modifier: Modifier = Modifier,
//    pergunta: String,
//    opcoes: List<String>,
//    respostaCorreta: String?,
//    onOpcaoSelecionada: (String) -> Unit
//) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surface
//        ),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        // Conteúdo do cartão
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(24.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            // Título da pergunta
//            Text(
//                text = pergunta,
//                fontSize = 20.sp,
//                color = MaterialTheme.colorScheme.onSurface
//            )
//
//            // Lista de opções
//            opcoes.forEach { opcao ->
//                OutlinedButton(
//                    onClick = { onOpcaoSelecionada(opcao) },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text(
//                        text = opcao,
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(vertical = 4.dp)
//                    )
//                }
//            }
//        }
//    }
//
//}


@Composable
fun CardPergunta(
    pergunta: String,
    opcoes: List<String>,
    respostaCorreta: String,
    opcaoSelecionada: String?,
    onOpcaoSelecionada: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = pergunta,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            opcoes.forEach { opcao ->
                // Define a cor do botão com base no clique
                val corBotao = when {
                    opcaoSelecionada == null -> MaterialTheme.colorScheme.surface // Cor normal
                    opcao == respostaCorreta -> Color(0xFF4CAF50) // Verde para a correta
                    opcao == opcaoSelecionada -> Color(0xFFE53935) // Vermelho se escolheu a errada
                    else -> MaterialTheme.colorScheme.surface
                }

                val corTexto = if (opcaoSelecionada != null && (opcao == respostaCorreta || opcao == opcaoSelecionada)) {
                    Color.White
                } else {
                    MaterialTheme.colorScheme.onSurface
                }

                Button(
                    onClick = {
                        // Só permite clicar se ainda não tiver selecionado nenhuma resposta nesta pergunta
                        if (opcaoSelecionada == null) {
                            onOpcaoSelecionada(opcao)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = corBotao)
                ) {
                    Text(
                        text = opcao,
                        fontSize = 16.sp,
                        color = corTexto,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}