package br.com.ajudafio

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

// O Koin da Web também é iniciado pelo KoinApplication dentro de App() (sharedUI).
// Não chamar initKoin aqui de novo, senão sobem 2 instâncias de Koin ao mesmo tempo.
@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        App()
    }
}