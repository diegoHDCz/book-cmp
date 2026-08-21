package br.com.ajudafio.presentation.book

import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.presentation.UiText

/**
 * Traduz erro de domínio -> texto de UI. Fica na presentation porque
 * mensagem é responsabilidade da UI, não do domain.
 * TODO: trocar DynamicString por UiText.StringResourceId quando houver
 * strings de recurso (i18n).
 */
fun DataError.Remote.toUiText(): UiText = UiText.DynamicString(
    when (this) {
        DataError.Remote.REQUEST_TIMEOUT -> "A requisição demorou demais. Tente novamente."
        DataError.Remote.TOO_MANY_REQUESTS -> "Muitas requisições. Aguarde um momento."
        DataError.Remote.NO_INTERNET -> "Sem conexão com a internet."
        DataError.Remote.SERVER -> "Erro no servidor. Tente mais tarde."
        DataError.Remote.SERIALIZATION -> "Não foi possível processar a resposta."
        DataError.Remote.UNKNOWN -> "Algo deu errado. Tente novamente."
    },
)
