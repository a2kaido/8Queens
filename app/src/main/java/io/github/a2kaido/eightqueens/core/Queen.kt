package io.github.a2kaido.eightqueens.core

data class Queen(
    val point: Point,
) {
    /**
     * @return points which Queen can move to
     */
    fun canMove(board: Board): List<Point> {
        TODO("Implement it")
    }
}
