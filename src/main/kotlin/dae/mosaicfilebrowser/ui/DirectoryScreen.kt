@file:Suppress("FunctionName")

package dae.mosaicfilebrowser.ui

import androidx.compose.runtime.Composable
import com.jakewharton.mosaic.Column
import com.jakewharton.mosaic.Row
import com.jakewharton.mosaic.Text
import dae.mosaicfilebrowser.state.MAX_FILES
import dae.mosaicfilebrowser.state.ScreenState

@Composable
fun DirectoryScreen(state: ScreenState.DirectoryState) {
    val files = state.children

    Column {
        HorizontalRule()
        Text("Path: ${state.file.absolutePath}")
        if (files.isEmpty()) {
            Text("No files")
        } else {
            files.forEachIndexed { index, file ->
                Text("($index) ${file.name}")
            }
            for (i in 0..(MAX_FILES - files.size)) {
                Text("")
            }
        }
        DirectoryCommands(state = state)
    }
}

@Composable
fun DirectoryCommands(state: ScreenState.DirectoryState) {
    Row {
        Text("(p)arent")
        if (state.hasNextPage) {
            Text(" (n)ext")
        }
        if (state.hasPreviousPage) {
            Text(" (b)ack")
        }
        Text(" (q)uit")
    }
}