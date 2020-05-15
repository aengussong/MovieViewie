package com.aengussong.imageloadrr

import android.widget.ImageView
import com.aengussong.imageloadrr.loader.Loader
import com.aengussong.imageloadrr.loader.defloader.DefaultImageLoader
import kotlinx.coroutines.*
import java.lang.ref.WeakReference
import kotlin.coroutines.CoroutineContext

/**
 * Image loader, suspend calls to [loader] dispatcher on [Dispatchers.Main]
 *
 * @param loader - [Loader] implementation, used for loading bitmaps from url, default value is
 * [DefaultImageLoader] with base caching possibility
 * */
class ImageLoadrr(private val loader: Loader = DefaultImageLoader()) {

    private val sJob = SupervisorJob()
    private val errorHandler = CoroutineExceptionHandler(::handleError)
    private val scope = CoroutineScope(Dispatchers.Main + sJob + errorHandler)

    /**
     * Loads image from url to [ImageView], stores weak reference for image. In error event do nothing
     * */
    fun loadImageInto(url: String, imageView: ImageView): Job {
        val weakImage = WeakReference(imageView)
        return scope.launch {
            val bitmap = loader.loadImage(url)
            weakImage.get()?.setImageBitmap(bitmap)
        }
    }

    /**
     * Cancel all child coroutines
     * */
    fun cancelCurrentLoading() = scope.coroutineContext.cancelChildren()

    private fun handleError(coroutineContext: CoroutineContext, throwable: Throwable) {
        print("Exception while trying to load image: ${throwable.message}")
    }

}