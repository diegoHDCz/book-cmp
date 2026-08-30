package br.com.ajudafio.di

import br.com.ajudafio.book.di.bookModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

/**
 * Módulos de domínio/dados (independentes de UI). Cada feature contribui aqui.
 */
val sharedModules = listOf(
    bookModule,
)

/**
 * Composition root. Chame uma vez no arranque de cada plataforma.
 * Os módulos de presentation (ViewModels) vivem no sharedUI e entram por
 * [extraModules], já que sharedLogic não pode depender do sharedUI.
 */
fun initKoin(
    extraModules: List<Module> = emptyList(),
    config: KoinAppDeclaration? = null,
) = startKoin {
    config?.invoke(this)
    modules(sharedModules + extraModules)
}
