package com.example.quiz

data class Pergunta(
    val id: Int,
    val titulo: String,
    val opcoes: List<String>,
    val respostaCorreta: String
) {
    val opcoesEmbaralhadas: List<String> get() = opcoes.shuffled()
}

val listaDePerguntas = listOf(
    Pergunta(
        id = 1,
        titulo = "Quantas patas tem um cachorro?",
        opcoes = listOf("1", "2", "3", "4"),
        respostaCorreta = "4"
    ),

    Pergunta(
        id = 2,
        titulo = "Qual é a capital do Brasil?",
        opcoes = listOf("São Paulo", "Brasília", "Rio de Janeiro", "Bahia"),
        respostaCorreta = "Brasília"
    ),

    Pergunta(
        id = 3,
        titulo = "Quem foi o primeiro homem a pisar na Lua?",
        opcoes = listOf("Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "Alan Shepard"),
        respostaCorreta = "Neil Armstrong"
    ),

    Pergunta(
        id = 4,
        titulo = "Quando o Brasil foi descoberto?",
        opcoes = listOf("1805", "1950", "1500", "1450"),
        respostaCorreta = "1500"
    ),

    Pergunta(
        id = 5,
        titulo = "Quantos planetas tem o sistema solar?",
        opcoes = listOf("8", "10", "7", "9"),
        respostaCorreta = "8"
    ),

    Pergunta(
        id = 6,
        titulo = "Qual o planeta é conhecido como planeta vermelho",
        opcoes = listOf("Vênus", "Marte", "Júpiter", "Saturno"),
        respostaCorreta = "Marte"
    ),
)