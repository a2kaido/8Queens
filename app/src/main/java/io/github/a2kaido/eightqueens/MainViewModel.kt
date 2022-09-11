package io.github.a2kaido.eightqueens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.a2kaido.eightqueens.core.Blank
import io.github.a2kaido.eightqueens.core.Board
import io.github.a2kaido.eightqueens.core.Point
import io.github.a2kaido.eightqueens.core.Queen
import io.github.a2kaido.eightqueens.core.QueenRange

class MainViewModel : ViewModel() {
    var state by mutableStateOf(QueensState())
        private set

    fun putQueen(point: Point) {
        val queenRange = Queen.moveRange(8, point)

        state = state.copy(
            board = state.board.mapIndexed { y, rows ->
                rows.mapIndexed { x, piece ->
                    when (Point(x, y)) {
                        point -> {
                            Queen
                        }
                        in queenRange -> {
                            QueenRange
                        }
                        else -> {
                            piece
                        }
                    }
                }.toList()
            }.toList()
        )
    }
}

data class QueensState(
    val board: Board = listOf(
        listOf(Blank, Blank, Blank, Blank, Blank, Blank, Blank, Blank),
        listOf(Blank, Blank, Blank, Blank, Blank, Blank, Blank, Blank),
        listOf(Blank, Blank, Blank, Blank, Blank, Blank, Blank, Blank),
        listOf(Blank, Blank, Blank, Blank, Blank, Blank, Blank, Blank),
        listOf(Blank, Blank, Blank, Blank, Blank, Blank, Blank, Blank),
        listOf(Blank, Blank, Blank, Blank, Blank, Blank, Blank, Blank),
        listOf(Blank, Blank, Blank, Blank, Blank, Blank, Blank, Blank),
        listOf(Blank, Blank, Blank, Blank, Blank, Blank, Blank, Blank),
    )
)
