package com.lambdaschool.favoritepicturesgallery

import android.net.Uri
import android.util.Log

import java.io.Serializable

class ImageData(fullPhotoUri: Uri) : Serializable {
    var name: String? = null
    var description: String? = null
    val fileUriString: String

    val fileUri: Uri
        get() = Uri.parse(fileUriString)
 //gets raw URI string from FileUriStrong designates it a URI jss

    init {
        this.fileUriString = fullPhotoUri.toString()
        val path = fullPhotoUri.path!!.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // and here i'm a little lost... JSS
        this.name = path[path.size - 1]
        //AFAICT this gets the second to last chunk of path to find the name of the file jss
        this.description = ""
    }
}
