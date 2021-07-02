package dae.mosaicfilebrowser.state

import java.io.File
import kotlin.math.max

const val MAX_FILES = 10

sealed class ScreenState {
    object SplashState : ScreenState()

    data class DirectoryState(val file: File, val page: Int = 0) : ScreenState() {
        val totalPages: Int by lazy {
            file.list().size / MAX_FILES
        }

        val children: List<File> by lazy {
            val files = file.listFiles() ?: emptyArray<File>()

            val startIndex = max(0, Integer.min(files.size - 1, page * MAX_FILES))
            val endIndex = Integer.min(files.size - 1, startIndex + MAX_FILES - 1)

            files.slice(startIndex..endIndex)
        }

        fun previousPage(): DirectoryState = DirectoryState(file, (page - 1).coerceIn(0 until totalPages))
        fun nextPage(): DirectoryState = DirectoryState(file, (page + 1).coerceIn(0 until totalPages))

        val hasPreviousPage: Boolean by lazy { page > 0 }
        val hasNextPage: Boolean by lazy { page < totalPages - 1 }
    }

    data class FileState(val file: File) : ScreenState()
}