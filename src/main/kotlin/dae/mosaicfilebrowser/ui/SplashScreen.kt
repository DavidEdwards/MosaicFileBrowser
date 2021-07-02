@file:Suppress("FunctionName")

package dae.mosaicfilebrowser.ui

import androidx.compose.runtime.Composable
import com.jakewharton.mosaic.Column
import com.jakewharton.mosaic.Text
import dae.mosaicfilebrowser.state.ScreenState

@Composable
fun SplashScreen(state: ScreenState.SplashState) {
    Column {
        HorizontalRule()
        Text(
            """
            This is a File Browser using Mosaic.
            
            Ideally I would like to be able to simply press a character, and have the program react to it. In this case I must make do entering a command letter and then pressing enter.
            
            Commands are highlighted with (). For example (q)uit. Means that pressing q followed by an enter will quit the program.
            
            You can delve deeper into a directory structure (or view basic file data) by entering the relevant number. Lists of files are paginated.
            
            Press enter to start.
        """.trimIndent()
        )
    }
}