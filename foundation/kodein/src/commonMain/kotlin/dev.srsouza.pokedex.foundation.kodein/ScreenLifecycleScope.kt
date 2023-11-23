package dev.srsouza.pokedex.foundation.kodein

import org.kodein.di.bindings.Scope
import org.kodein.di.bindings.ScopeRegistry
import org.kodein.di.bindings.StandardScopeRegistry
import org.kodein.di.internal.synchronizedIfNull

class ScreenScopeContext(
    val navigatorContext: NavigatorScopeContext,
    val uniqueId: String,
    val onRegistryScope: (self: ScreenScopeContext) -> Unit = {},
) {
    internal var disposeCallback: () -> Unit = {}

    public fun onRegistryScope() {
        onRegistryScope.invoke(this)
    }

    override fun hashCode(): Int = uniqueId.hashCode()

    override fun equals(other: Any?): Boolean {
        return (other as? ScreenScopeContext?)?.uniqueId.equals(uniqueId)
    }

    public fun dispose() {
        disposeCallback()
        disposeCallback = {}
    }
}

open class ScreenLifecycleScope private constructor(
    private val newRegistry: () -> ScopeRegistry
) : Scope<ScreenScopeContext> {
    public companion object multiItem : ScreenLifecycleScope(::StandardScopeRegistry)

    private val map = HashMap<ScreenScopeContext, ScopeRegistry>()

    override fun getRegistry(context: ScreenScopeContext): ScopeRegistry {
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