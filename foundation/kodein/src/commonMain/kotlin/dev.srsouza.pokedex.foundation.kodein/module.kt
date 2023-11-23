package dev.srsouza.pokedex.foundation.kodein

import org.kodein.di.DI
import org.kodein.di.registerContextTranslator

val kodeinScopesModule: DI.Module = DI.Module("kodein-scopes") {
    registerContextTranslator<ScreenScopeContext, NavigatorScopeContext> { it.navigatorContext }
}