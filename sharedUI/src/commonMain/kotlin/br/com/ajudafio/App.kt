package br.com.ajudafio

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.ajudafio.theme.AppTheme
import br.com.ajudafio.ui.greeting.GreetingScreen

@Composable
@Preview
fun App() {
    AppTheme {
        GreetingScreen()
    }
}
