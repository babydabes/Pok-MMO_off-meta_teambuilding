package com.pokemmo.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pokemmo.core.ui.theme.typeColor

@Composable
fun TypeBadge(
    typeName: String,
    modifier: Modifier = Modifier,
) {
    val bgColor = typeColor(typeName)
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(bgColor)
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = typeName.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
        )
    }
}
