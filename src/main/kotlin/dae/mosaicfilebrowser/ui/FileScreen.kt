@file:Suppress("FunctionName")

package dae.mosaicfilebrowser.ui

import androidx.compose.runtime.Composable
import com.jakewharton.mosaic.Column
import com.jakewharton.mosaic.Text
import dae.mosaicfilebrowser.extensions.modifiedTime
import dae.mosaicfilebrowser.state.MAX_FILES
import dae.mosaicfilebrowser.state.ScreenState

@Composable
fun FileScreen(state: ScreenState.FileState) {
    Column {
        HorizontalRule()
        Text("Path: ${state.file.absolutePath}")
        Text("Name: ${state.file.name}")
        Text("Size: ${state.file.length()} B")
        Text("Modified: ${state.file.modifiedTime()}")
        Text("(p)arent (o)pen (q)uit")

        for (i in 0..(MAX_FILES - 4)) {
            Text("")
        }
    }
}