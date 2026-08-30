package br.com.ajudafio

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import br.com.ajudafio.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()
    ComposeViewport {
        App()
    }
}