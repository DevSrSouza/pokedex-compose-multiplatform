package dev.srsouza.pokedex.foundation.kodein

import org.kodein.di.DI
import org.kodein.di.bindings.NoArgBindingDI
import org.kodein.di.scoped
import org.kodein.di.singleton

inline fun <reified T : Any> DI.Builder.bindNavigatorScopedSingleton(
    tag: Any? = null,
    overrides: Boolean? = null,
    noinline creator: NoArgBindingDI<NavigatorScopeContext>.() -> T
) {
    Bind(tag, overrides, scoped(NavigatorLifecycleContext.multiItem).singleton(creator = creator))
}

inline fun <reified T : Any> DI.Builder.bindScreenScopedSingleton(
    tag: Any? = null,
    overrides: Boolean? = null,
    noinline creator: NoArgBindingDI<ScreenScopeContext>.() -> T
) {
    Bind(tag, overrides, scoped(ScreenLifecycleScope).singleton(creator = creator))
}
