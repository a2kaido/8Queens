package io.github.a2kaido.eightqueens

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.a2kaido.eightqueens.ui.theme.EightQueensAndroidTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EightQueenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun run() {
        composeTestRule.setContent {
            EightQueensAndroidTheme {
                ChessBoard()
            }
        }

        putQueen()
    }

    private fun putQueen(depth: Int = 0) {
        var cursor = 0
        while (true) {
            // cursorが8で終わり
            if (cursor > 7) return

            val position = depth * 8 + cursor

            if (!isEmptyNode(position = position)) {
                cursor++
                continue
            }

            // 一個置く
            putQueenAt(position = position)

            // 修了条件
            if (countQueen() == 8) return

            // 子ども呼び出し
            putQueen(depth + 1)

            // 修了条件
            if (countQueen() == 8) return

            // 子どもがダメだったので1手戻して次に進む
            back()
            cursor++
        }
    }

    private fun back() = composeTestRule
        .onNodeWithTag("back")
        .performClick()

    private fun isEmptyNode(position: Int) = composeTestRule
        .onNodeWithTag("board")
        .onChildren()[position]
        .run {
            runCatching {
                assert(hasText(""))
            }.isSuccess
        }

    private fun putQueenAt(position: Int) = composeTestRule
        .onNodeWithTag("board")
        .onChildren()[position]
        .performClick()

    private fun countQueen() = composeTestRule
        .onNodeWithTag("board")
        .onChildren()
        .filter(hasText("Q"))
        .fetchSemanticsNodes()
        .size
}