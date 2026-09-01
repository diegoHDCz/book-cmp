package br.com.ajudafio

import android.app.Application

// O Koin do Android é iniciado pelo KoinApplication dentro de App() (sharedUI),
// que é o composition root único para Android e Web. Não chamar initKoin aqui
// de novo, senão sobem 2 instâncias de Koin ao mesmo tempo.
class BookApplication: Application()