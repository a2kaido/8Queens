package io.github.a2kaido.eightqueens.ui.atoms

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Cell(
    text: String,
    onClickCell: () -> Unit,
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(1.dp, Color.Black)
            .clickable(enabled = true, onClick = onClickCell),
        contentAlignment = Alignment.Center,
    ) {
        Text(text)
    }
}
