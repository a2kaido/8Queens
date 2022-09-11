package io.github.a2kaido.eightqueens.ui.atoms

import androidx.compose.foundation.background
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
import io.github.a2kaido.eightqueens.core.Piece
import io.github.a2kaido.eightqueens.core.Queen

@Composable
fun Cell(
    piece: Piece,
    onClickCell: () -> Unit,
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(1.dp, Color.Black)
            .clickable(enabled = true, onClick = onClickCell)
            .background(color = if (piece is Queen) Color.Green else Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Text(piece.toDisplay())
    }
}
