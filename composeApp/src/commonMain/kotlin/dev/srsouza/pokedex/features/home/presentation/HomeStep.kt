package dev.srsouza.pokedex.features.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.lyricist.rememberStrings
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.srsouza.pokedex.features.pokemonList.presentation.PokemonListStep
import dev.srsouza.pokedex.navigation.Step

object HomeStep : Step() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val strings = rememberStrings().strings.home
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                text = strings.title,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
            )

            Column(modifier = Modifier.padding(top = 32.dp)) {
                Card(
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            navigator.push(PokemonListStep)
                        }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = strings.pokemonListCardTitle,
                            modifier = Modifier.padding(all = 12.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }

                }
            }
        }
    }
}