package com.example.ceilingmeasurer.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.annotation.NonNull
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class ImageSaver(var context: Context) {
    private var fileName = "image.png"

    fun setFileName(fileName: String): ImageSaver {
        this.fileName = fileName
        return this
    }

    fun save(bitmapImage: Bitmap?) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(createFile())
            bitmapImage?.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fileOutputStream?.close()
        }
    }

    @NonNull
    private fun createFile(): File {
        val directory: File = context.getDir(DIRECTORY_NAME, Context.MODE_PRIVATE)
        if (!directory.exists() && !directory.mkdirs()) {
            Log.e("ImageSaver", "Error creating directory $directory")
        }
        return File(directory, fileName)
    }

    fun load(): Bitmap? {
        var inputStream: FileInputStream? = null
        try {
            inputStream = FileInputStream(createFile())
            return BitmapFactory.decodeStream(inputStream)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }
        return null
    }

    //Не забудь setFileName того файла что собираемся удалить
    fun deleteFile() = createFile().delete()

    companion object {
        private const val DIRECTORY_NAME = "images"
    }
}