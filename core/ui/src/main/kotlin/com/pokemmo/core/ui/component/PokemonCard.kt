package com.pokemmo.core.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.pokemmo.core.domain.model.Pokemon
import com.pokemmo.core.ui.theme.typeColor

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val primaryTypeColor = typeColor(pokemon.types.first().name)

    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().aspectRatio(0.78f),
        colors = CardDefaults.cardColors(
            containerColor = primaryTypeColor.copy(alpha = 0.12f),
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp).fillMaxSize(),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.spriteUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = pokemon.displayName,
                modifier = Modifier.size(72.dp),
            )

            Text(
                text = "#${pokemon.id.toString().padStart(3, '0')}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = pokemon.displayName,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(4.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                pokemon.types.forEach { TypeBadge(typeName = it.name) }
            }

            Spacer(Modifier.height(4.dp))

            pokemon.tier?.let {
                AssistChip(
                    onClick = {},
                    label = {
                        Text(it.label, style = MaterialTheme.typography.labelSmall)
                    },
                    modifier = Modifier.height(22.dp),
                )
            }
        }
    }
}
