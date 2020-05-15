package com.aengussong.imageloadrr

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ImageLoadrrTest {

    @Test
    fun loadImage_shouldLoadImage() = runBlocking {
        val url = "http://httpbin.org/image/png"

        val result = loadIntoView(url)

        Assert.assertNotNull(result)
    }

    @Test
    fun loadError_shouldDoNothing() = runBlocking {
        val url = "http://httpbin.org/get/status/500"

        val result = loadIntoView(url)

        Assert.assertNull(result)
    }

    @Test
    fun loadByNotImageUrl_shouldDoNothing() = runBlocking {
        val url = "http://httpbin.org/get"

        val result = loadIntoView(url)

        Assert.assertNull(result)
    }

    private suspend fun loadIntoView(url:String): Bitmap?{
        val imageView = ImageView(InstrumentationRegistry.getInstrumentation().context)

        ImageLoadrr()
            .loadImageInto(url, imageView)
            .join()

        return imageView.drawable?.toBitmap()
    }

}