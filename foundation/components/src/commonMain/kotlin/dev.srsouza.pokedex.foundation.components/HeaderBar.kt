package dev.srsouza.pokedex.foundation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowNarrowLeft
import compose.icons.tablericons.X

enum class NavigationIcon(val icon: ImageVector) {
    CLOSE(TablerIcons.X),
    BACK(TablerIcons.ArrowNarrowLeft)
}

@Composable
fun HeaderBar(
    navigationIcon: NavigationIcon,
    navigationAction: () -> Unit,
    title: String? = null,
    rightActions: @Composable RowScope.() -> Unit = {},
) {
    Row(
        modifier = Modifier.padding(
            end = 8.dp,
            top = 8.dp,
            bottom = 8.dp,
        )
    ) {
        IconButton(
            onClick = { navigationAction() },
        ) {
            Icon(
                imageVector = navigationIcon.icon,
                contentDescription = "Navigation button",
                modifier = Modifier.size(24.dp)
            )
        }
        if(title != null) {
            Spacer(Modifier.size(8.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(weight = 1f)
        Row {
            rightActions()
        }
    }
}
