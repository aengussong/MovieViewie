package com.aengussong.imageloadrr.loader

import android.graphics.Bitmap
import com.aengussong.imageloadrr.ImageLoadrr

/**
 * Image loader main interface. Loaders to be used in [ImageLoadrr] should implement this interface
 * */
interface Loader {
    suspend fun loadImage(url:String): Bitmap
}