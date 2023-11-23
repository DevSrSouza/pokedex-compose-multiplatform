package dev.srsouza.pokedex.features.pokemonList.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.rememberStrings
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberImagePainter
import dev.srsouza.pokedex.features.pokemonList.domain.PokemonListItem
import dev.srsouza.pokedex.foundation.components.circularProgressLoadingItem
import dev.srsouza.pokedex.foundation.components.rememberLazyGridListPaging
import dev.srsouza.pokedex.navigation.HeaderOptions
import dev.srsouza.pokedex.navigation.StepStateful
import org.kodein.di.compose.rememberInstance

object PokemonListStep : StepStateful<PokemonListViewModel, PokemonListState>() {
    override val headerOptions: HeaderOptions
        @Composable
        get() {
            val strings = rememberStrings().strings.home
            return remember {
                HeaderOptions(title = strings.title)
            }
        }

    @Composable
    override fun initializeViewModel(): PokemonListViewModel {
        val viewModel by rememberInstance<PokemonListViewModel>()

        LaunchedEffect(Unit) {
            viewModel.loadItems()
        }

        return viewModel
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content(viewModel: PokemonListViewModel, state: PokemonListState) {
        val navigator = LocalNavigator.currentOrThrow

        // TODO: implement search
        val paginatedLazyGridState = rememberLazyGridListPaging(
            loadNext = viewModel::loadItems,
            isLoading = state.isPageLoading,
            shouldLoadMore = state.shouldLoadMore,
        )
        val isLoadingState = remember(state) {
            derivedStateOf { state.isPageLoading }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = paginatedLazyGridState,
        ) {
            items(state.items) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    onClick = {
                        // TODO: navigate to detail
                    },
                    modifier = Modifier.padding(8.dp).animateItemPlacement(),
                )
            }
            circularProgressLoadingItem(isLoadingState = isLoadingState)
        }
    }
}

@Composable
private fun PokemonCard(
    pokemon: PokemonListItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .size(150.dp)
        .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val spriteUrl = pokemon.spriteUrl
            if(spriteUrl != null) {
                val painter = rememberImagePainter(pokemon.spriteUrl)
                Image(
                    painter = painter,
                    contentDescription = "Pokemon ${pokemon.name} image",
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
            }

            Text(
                text = pokemon.name,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}