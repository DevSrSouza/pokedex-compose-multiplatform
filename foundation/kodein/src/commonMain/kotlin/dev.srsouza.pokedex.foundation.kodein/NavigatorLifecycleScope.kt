package dev.srsouza.pokedex.foundation.kodein

import org.kodein.di.bindings.Scope
import org.kodein.di.bindings.ScopeRegistry
import org.kodein.di.bindings.StandardScopeRegistry
import org.kodein.di.internal.synchronizedIfNull

class NavigatorScopeContext(
    val uniqueId: String,
    val onRegistryScope: (self: NavigatorScopeContext) -> Unit = {},
) {
    internal var disposeCallback: () -> Unit = {}

    public fun onRegistryScope() {
        onRegistryScope.invoke(this)
    }

    override fun hashCode(): Int = uniqueId.hashCode()

    override fun equals(other: Any?): Boolean {
        return (other as? NavigatorScopeContext?)?.uniqueId?.equals(uniqueId) == true
    }

    public fun dispose() {
        disposeCallback()
        disposeCallback = {}
    }
}

open class NavigatorLifecycleContext private constructor(
    private val newRegistry: () -> ScopeRegistry
) : Scope<NavigatorScopeContext> {
    companion object multiItem : NavigatorLifecycleContext(::StandardScopeRegistry)

    private val map = mutableMapOf<NavigatorScopeContext, ScopeRegistry>()

    override fun getRegistry(context: NavigatorScopeContext): ScopeRegistry {
        return synchronizedIfNull(
            lock = map,
            predicate = { map[context] },
            ifNotNull = { it },
            ifNull = {
                val registry = newRegistry()
                map[context] = registry
                context.disposeCallback = {
                    registry.clear()
                    map.remove(context)
                }
                context.onRegistryScope()
                registry
            }
        )
    }
}