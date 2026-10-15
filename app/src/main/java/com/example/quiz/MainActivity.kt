package com.example.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quiz.screens.DadosScreen
import com.example.quiz.screens.HomeScreen
import com.example.quiz.screens.QuizScreen
import com.example.quiz.ui.theme.QuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable(route = "home") { HomeScreen(navController = navController) }

                        composable(route = "dados") { DadosScreen(navController = navController) }

                        composable(route = "quiz/{nome}") { backStackEntry ->
                            val nomeJogador = backStackEntry.arguments?.getString("nome") ?: "Jogador"

                            QuizScreen(navController = navController, nomeJogador = nomeJogador) }
                    }
                }
            }
        }
    }
}

