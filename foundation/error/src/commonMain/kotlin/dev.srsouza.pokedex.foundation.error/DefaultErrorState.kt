package dev.srsouza.pokedex.foundation.error

import compose.icons.TablerIcons
import compose.icons.tablericons.X

public fun defaultErrorState(
    onRetry: () -> Unit
): ErrorState {
    return ErrorState(  // TODO: configurable
        illustration = TablerIcons.X,
        title = "Erro",
        button = ErrorStateButton(
            title = "Tentar novamente",
            action = { onRetry() }
        )
    )
}