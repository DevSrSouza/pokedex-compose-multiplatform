package dev.srsouza.pokedex.foundation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.srsouza.pokedex.foundation.error.ErrorState

@Composable
fun StepError(
    state: ErrorState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            imageVector = state.illustration,
            contentDescription = "Error Illustration",
            modifier = Modifier.size(150.dp),
        )
        Spacer(Modifier.size(8.dp))
        Text(
            text = state.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(weight = 1f)
        Button(state.button.action, modifier = Modifier.fillMaxWidth()) {
            Text(state.button.title)
        }
    }
}