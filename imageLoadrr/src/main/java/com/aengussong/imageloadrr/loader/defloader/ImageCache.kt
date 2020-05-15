package com.aengussong.imageloadrr.loader.defloader

import android.graphics.Bitmap
import java.lang.ref.SoftReference

/**
 * Base image cache implementation. Stores images in memory, holding reference via SoftReference.
 * */
class ImageCache {

    private val cache = mutableMapOf<String, SoftReference<Bitmap>>()

    fun getImage(url: String) = cache[url]?.get()

    fun storeImage(url: String, bitmap: Bitmap) {
        cache[url] = SoftReference(bitmap)
    }

    fun clear() = cache.clear()
}