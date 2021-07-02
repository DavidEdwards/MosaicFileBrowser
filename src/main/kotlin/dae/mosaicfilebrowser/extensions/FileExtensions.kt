package dae.mosaicfilebrowser.extensions

import java.io.File
import java.time.Instant

fun File.modifiedTime(): Instant = Instant.ofEpochMilli(this.lastModified())