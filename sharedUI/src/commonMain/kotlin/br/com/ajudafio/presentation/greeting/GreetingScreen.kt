package br.com.ajudafio.presentation.greeting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.ajudafio.di.AppContainer
import org.jetbrains.compose.resources.painterResource

import ajudafio_cmp.sharedui.generated.resources.Res
import ajudafio_cmp.sharedui.generated.resources.compose_multiplatform
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

/**
 * Tela de exemplo ligando presentation -> domain (usecase) -> data (repository).
 * Sirva de referência para as próximas features em ui/<feature>/.
 */
@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    var showContent by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(
                modifier = Modifier.padding(vertical = 8.dp),
                onClick = { showContent = !showContent },
            ) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { AppContainer.getGreetingUseCase().message }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}
