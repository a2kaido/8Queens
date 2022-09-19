package io.github.a2kaido.eightqueens

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
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
        val startTime = System.currentTimeMillis()

        putQueen()
        val time = System.currentTimeMillis() - startTime

        val result = "${time.toString().dropLast(3)}.${time.toString().takeLast(3)}"
        println("Result: $result")
        inputResult(result)
        composeTestRule.waitForIdle()

        Thread.sleep(3000)
    }

    private fun putQueen(depth: Int = 0) {
        var cursor = 0
        while (true) {
            // cursorが8で終わり
            if (cursor > 7) return

            // 置く場所
            val position = depth * 8 + cursor

            // 置けるか確認。ダメなら隣に移動。
            if (!isEmptyNode(position = position)) {
                cursor++
                continue
            }

            // 置く
            putQueenAt(position = position)

            // 8個置いたら終わり
            if (countQueen() == 8) return

            // 下の段に移動
            putQueen(depth + 1)

            // 8個置いたら終わり
            if (countQueen() == 8) return

            // ダメだったので1手戻して次に進む
            back()
            cursor++
        }
    }

    private fun inputResult(result: String) = composeTestRule
        .onNodeWithTag("result")
        .performTextInput(result)

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