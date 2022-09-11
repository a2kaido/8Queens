package io.github.a2kaido.eightqueens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.a2kaido.eightqueens.core.Point
import io.github.a2kaido.eightqueens.ui.atoms.Cell
import io.github.a2kaido.eightqueens.ui.theme.EightQueensAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EightQueensAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ChessBoard()
                }
            }
        }
    }
}

@Composable
fun ChessBoard(
    viewModel: MainViewModel = viewModel()
) {
    val state = viewModel.state
    val putQueen = remember { { x: Int, y: Int -> { viewModel.putQueen(Point(x, y)) } } }

    LazyVerticalGrid(
        columns = GridCells.Fixed(8)
    ) {
        state.pieces.forEachIndexed { y, row ->
            itemsIndexed(row) { x, cell ->
                val putQueen2 = remember { putQueen(x, y) }
                Cell(
                    text = cell,
                    onClickCell = putQueen2,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EightQueensAndroidTheme {
        ChessBoard()
    }
}