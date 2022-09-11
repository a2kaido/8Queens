package io.github.a2kaido.eightqueens.core

sealed interface Piece {
    fun toDisplay(): String
}
