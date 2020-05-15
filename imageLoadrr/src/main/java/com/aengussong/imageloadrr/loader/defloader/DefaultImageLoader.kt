package com.aengussong.imageloadrr.loader.defloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.aengussong.imageloadrr.loader.Loader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

/**
 * Default [Loader] implementation. Retrieves images from url via [UrlConnection] input stream.
 * Uses basic caching, images retrieved from network stored in in-memory cache, which would be
 * cleaned on memory shortage.
 * */
class DefaultImageLoader : Loader {

    private val cache = ImageCache()

    /**
     * Retrieves bitmap from cache, or, if cache is empty, makes network request to [url]
     * */
    override suspend fun loadImage(url: String) = getFromCache(url) ?: loadFromNetwork(url)

    fun cleanCache() = cache.clear()

    private fun getFromCache(url: String) = cache.getImage(url)

    private suspend fun loadFromNetwork(url: String) = withContext<Bitmap>(Dispatchers.IO) {
        val bitmap = URL(url).openStream().use { BitmapFactory.decodeStream(it) }
        cache.storeImage(url, bitmap)
        bitmap
    }

}