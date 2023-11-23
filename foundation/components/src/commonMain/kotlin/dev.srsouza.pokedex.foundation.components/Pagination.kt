package dev.srsouza.pokedex.foundation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.filter

@Composable
fun rememberLazyGridListPaging(
    loadNext: () -> Unit,
    isLoading: Boolean,
    shouldLoadMore: Boolean,
    buffer: Int = 6,
): LazyGridState {
    val gridState = rememberLazyGridState()

    val shouldLoadNext = remember(isLoading, shouldLoadMore) {
        derivedStateOf {
            if (isLoading.or(shouldLoadMore.not())) {
                false
            } else {
                val layoutInfo = gridState.layoutInfo
                val totalItemsNumber = layoutInfo.totalItemsCount
                val lastVisibleItemIndex =
                    (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

                lastVisibleItemIndex >= (totalItemsNumber - buffer)
            }
        }
    }

    LaunchedEffect(shouldLoadNext) {
        snapshotFlow { shouldLoadNext.value }
            .filter { shouldLoadNext -> shouldLoadNext }
            .collect {
                loadNext()
            }
    }

    return gridState
}

fun LazyGridScope.circularProgressLoadingItem(
    isLoadingState: State<Boolean>
) {
    item(
        span = {
            GridItemSpan(maxLineSpan)
        }
    ) {
        val state by isLoadingState
        if(state) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}