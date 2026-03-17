package com.pokemmo.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pokemmo.core.domain.model.CoverageResult
import com.pokemmo.core.ui.theme.typeColor

@Composable
fun TypeCoverageStrip(
    coverage: CoverageResult,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = "Type Coverage",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(Modifier.height(4.dp))

        // Offensive coverage — scrollable row of type pills
        Text("Offensive", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(2.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            items(coverage.offensiveMultipliers.entries.sortedBy { it.key }) { (typeName, mult) ->
                CoveragePill(typeName = typeName, multiplier = mult, isOffensive = true)
            }
        }

        Spacer(Modifier.height(6.dp))

        // Defensive weaknesses
        Text("Weak To", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(2.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            val weak = coverage.defensiveWeaknesses.filter { it.value > 0 }.entries.sortedByDescending { it.value }
            items(weak) { (typeName, count) ->
                CoveragePill(typeName = typeName, count = count)
            }
        }
    }
}

@Composable
private fun CoveragePill(
    typeName: String,
    multiplier: Float = 0f,
    count: Int = 0,
    isOffensive: Boolean = false,
) {
    val bg = typeColor(typeName)
    val alpha = when {
        isOffensive && multiplier >= 2f -> 1f
        isOffensive && multiplier >= 1f -> 0.5f
        isOffensive -> 0.2f
        else -> 0.8f
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(bg.copy(alpha = alpha))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = typeName.take(3).uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
        )
        if (!isOffensive && count > 0) {
            Spacer(Modifier.width(2.dp))
            Text(
                text = "×$count",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
            )
        }
    }
}
