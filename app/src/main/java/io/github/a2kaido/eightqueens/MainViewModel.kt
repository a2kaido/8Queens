package io.github.a2kaido.eightqueens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.a2kaido.eightqueens.core.Point

class MainViewModel : ViewModel() {
    var state by mutableStateOf(QueensState())
        private set

    fun putQueen(point: Point) {
        state = state.copy(
            pieces = state.pieces.mapIndexed { y, rows ->
                rows.mapIndexed { x, text ->
                    if (x == point.x && y == point.y) "Q" else text
                }.toList()
            }.toList()
        )
    }
}

data class QueensState(
    val pieces: List<List<String>> = listOf(
        listOf("", "", "", "", "", "", "", ""),
        listOf("", "", "", "", "", "", "", ""),
        listOf("", "", "", "", "", "", "", ""),
        listOf("", "", "", "", "", "", "", ""),
        listOf("", "", "", "", "", "", "", ""),
        listOf("", "", "", "", "", "", "", ""),
        listOf("", "", "", "", "", "", "", ""),
        listOf("", "", "", "", "", "", "", ""),
    )
)
