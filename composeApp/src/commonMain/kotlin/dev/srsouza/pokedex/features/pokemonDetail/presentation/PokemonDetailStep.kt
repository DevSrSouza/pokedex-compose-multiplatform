package dev.srsouza.pokedex.features.pokemonDetail.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberImagePainter
import dev.srsouza.pokedex.features.pokemonDetail.domain.Pokemon
import dev.srsouza.pokedex.navigation.StepStateful
import org.kodein.di.compose.rememberInstance

data class PokemonDetailStep(
    val pokemonId: String,
) : StepStateful<PokemonDetailViewModel, PokemonDetailState>() {

    @Composable
    override fun initializeViewModel(): PokemonDetailViewModel {
        val viewModel by rememberInstance<PokemonDetailViewModel>()

        LaunchedEffect(Unit) {
            viewModel.fetchDetail(pokemonId)
        }

        return viewModel
    }

    @Composable
    override fun Content(
        viewModel: PokemonDetailViewModel,
        state: PokemonDetailState,
    ) {
        val pokemon = state.pokemon
        if(pokemon != null) {
            PokemonDetail(pokemon = pokemon)
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        }
    }

}

@Composable
private fun PokemonDetail(pokemon: Pokemon) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val spriteUrl = pokemon.spriteUrl

        if(spriteUrl != null) {
            val image = rememberImagePainter(spriteUrl)
            Image(
                painter = image,
                contentDescription = "Pokemon ${pokemon.name} sprite",
                modifier = Modifier.size(150.dp).padding(16.dp),
                contentScale = ContentScale.FillBounds,
            )
        }
        Text(
            text = pokemon.name,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        Box(
            modifier = Modifier.padding(12.dp)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.onSurface),
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Row {
                    Text(text = "Weight:")
                    Spacer(Modifier.size(4.dp))
                    Text(text = pokemon.weight.toString())
                }
            }
        }

        PokemonTypeGrid(pokemon)
    }
}

@Composable
private fun PokemonTypeGrid(pokemon: Pokemon) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp),
    ) {
        items(pokemon.types) { type ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = type.name,
                        modifier = Modifier.padding(8.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}
