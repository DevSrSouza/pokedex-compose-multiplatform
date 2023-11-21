import androidx.compose.ui.window.ComposeUIViewController
import dev.srsouza.pokedex.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }