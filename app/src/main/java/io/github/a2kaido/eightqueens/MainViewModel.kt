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

    private val stack: ArrayDeque<Board> = ArrayDeque()

    fun putQueen(point: Point) {
        if (state.board[point.y][point.x] !is Blank) return

        val queenRange = Queen.moveRange(8, point)

        stack.add(state.board)

        val newBoard = state.board.mapIndexed { y, rows ->
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

        state = state.copy(
            board = newBoard,
        )
    }

    fun back() {
        if (stack.isEmpty()) return

        state = state.copy(
            board = stack.removeLast()
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
