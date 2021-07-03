@file:Suppress("FunctionName")

package dae.mosaicfilebrowser

import com.jakewharton.mosaic.runMosaic
import dae.mosaicfilebrowser.model.StateViewModel
import dae.mosaicfilebrowser.state.ScreenState.*
import dae.mosaicfilebrowser.ui.DirectoryScreen
import dae.mosaicfilebrowser.ui.FileScreen
import dae.mosaicfilebrowser.ui.SplashScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jline.terminal.TerminalBuilder
import java.awt.Desktop
import java.io.File

val stateViewModel = StateViewModel()

fun main(args: Array<String>) = runMosaic {
    setContent {
        when (val screenState = stateViewModel.screenState) {
            is SplashState -> SplashScreen(state = screenState)
            is DirectoryState -> DirectoryScreen(state = screenState)
            is FileState -> FileScreen(state = screenState)
        }
    }

    inputController { code ->
        val s = code.toChar().toString()
        val screenState = stateViewModel.screenState

        if (screenState is SplashState) {
            stateViewModel.screenState = DirectoryState(File(System.getProperty("user.dir")))
            return@inputController true
        }

        when (s) {
            "q" -> false
            "p" -> {
                if (screenState is DirectoryState) {
                    screenState.file.parentFile?.let { parent ->
                        stateViewModel.screenState = DirectoryState(parent)
                    }
                } else if (screenState is FileState) {
                    screenState.file.parentFile?.let { parent ->
                        stateViewModel.screenState = DirectoryState(parent)
                    }
                }
                true
            }
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" -> {
                if (screenState is DirectoryState) {
                    val index = s.toInt()
                    if (screenState.children.isNotEmpty() && index in screenState.children.indices) {
                        val file = screenState.children[index]
                        if (file.isFile) {
                            stateViewModel.screenState = FileState(file)
                        } else {
                            stateViewModel.screenState = DirectoryState(file)
                        }
                    }
                }

                true
            }
            "n" -> {
                if (screenState is DirectoryState) {
                    stateViewModel.screenState = screenState.nextPage()
                }
                true
            }
            "b" -> {
                if (screenState is DirectoryState) {
                    stateViewModel.screenState = screenState.previousPage()
                }
                true
            }
            "o" -> {
                if (screenState is FileState) {
                    withContext(Dispatchers.IO) {
                        Desktop.getDesktop().open(screenState.file)
                    }
                }
                true
            }
            else -> true
        }
    }
}

suspend fun inputController(block: suspend (code: Int) -> Boolean) {
    withContext(Dispatchers.IO) {
        val terminal = TerminalBuilder.terminal()
        terminal.enterRawMode()
        val reader = terminal.reader()
        var reading = true

        while (reading) {
            val code = reader.read()
            reading = block.invoke(code)
        }
    }
}








