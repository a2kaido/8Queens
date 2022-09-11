package io.github.a2kaido.eightqueens.core

object Queen : Piece {
    override fun toDisplay() = "Q"

    /**
     * @return points which Queen can move to
     */
    fun moveRange(boardSize: Int, position: Point): List<Point> {
        val result = mutableListOf<Point>()

        for (cursor in 1..boardSize) {
            listOf(
                Point(position.x - cursor, position.y - cursor),
                Point(position.x, position.y - cursor),
                Point(position.x + cursor, position.y - cursor),
                Point(position.x - cursor, position.y),
                Point(position.x + cursor, position.y),
                Point(position.x - cursor, position.y + cursor),
                Point(position.x, position.y + cursor),
                Point(position.x + cursor, position.y + cursor),
            ).forEach {
                if (it.inBoard(8)) result.add(it)
            }
        }

        return result
    }
}

private fun Point.inBoard(boardSize: Int) = x in 0..boardSize && y in 0..boardSize
