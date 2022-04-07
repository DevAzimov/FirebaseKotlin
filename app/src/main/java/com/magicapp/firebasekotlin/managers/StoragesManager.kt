package com.magicapp.firebasekotlin.managers

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.net.URI

class StoragesManager {
    companion object {
        val storage = FirebaseStorage.getInstance()
        var storageRef = storage.reference
        var photosRef = storageRef.child("photos")

        fun uploadPhoto(uri: Uri, handler: StorageHandler) {
            val filename = System.currentTimeMillis().toString() + ".png"
            val uploadTask: UploadTask = photosRef.child(filename).putFile(uri)
            uploadTask.addOnSuccessListener { it ->
                val result = it.metadata!!.reference!!.downloadUrl
                result.addOnSuccessListener {
                    var imgUrl = it.toString()
                    handler.onSuccess(imgUrl)
                }.addOnFailureListener { e ->
                    handler.onError(e)
                }
            }.addOnFailureListener { e ->
                handler.onError(e)
            }
        }
    }
}