package com.example.safetymanagement2022.common

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.io.File
import java.net.URL

class MultiUploaderS3Client(private val bucketName:String) {

    val arrImage = mutableMapOf<String, String>()

    fun uploadMultiple(fileToKeyUploads: MutableMap<String,File>, transferUtility: TransferUtility): Completable {
        return transferUtility(transferUtility)
            .flatMapCompletable { transferUtility ->
                Observable.fromIterable(fileToKeyUploads.entries)
                    .flatMapCompletable { entry ->
                        uploadSingle(
                            transferUtility,
                            entry.value,
                            entry.key
                        )
                    }
            }
    }
//    fun downloadMultiple(fileToKeyUploads: MutableMap<String, File>, transferUtility: TransferUtility): Completable {
//        return transferUtility(transferUtility)
//            .flatMapCompletable {
//                Observable.fromIterable(fileToKeyUploads.entries)
//                    .flatMapCompletable { entry ->
//                        Log.d("mmm multi", entry.key.toString())
//                        downloadSingle(entry.key)
//                    }
//            }
//    }

    private fun transferUtility(transferUtility: TransferUtility): Single<TransferUtility?> {
        return Single.create { emitter ->
            emitter.onSuccess(
                transferUtility
            )
        }
    }


    private fun uploadSingle(
        transferUtility: TransferUtility?,
        aLocalFile: File?,
        toRemoteKey: String?
    ): Completable? {
        return Completable.create { emitter ->
            transferUtility?.upload(bucketName,toRemoteKey, aLocalFile)
                ?.setTransferListener(object : TransferListener {
                    override fun onStateChanged(id: Int, state: TransferState?) {
                        if (state == TransferState.COMPLETED) {
//                            val s3Url: URL = s3Client.getUrl("detectus/$USER_ID/photo", fileName)
//                            arrImage[index].imageUrl = s3Url.toString()]
                        }
                    }
                    override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
                        val done = bytesCurrent / bytesTotal * 100.0
                        Log.d(TAG, "UPLOAD - - ID: $id, percent done = $done")
                    }
                    override fun onError(id: Int, ex: Exception?) {
                        Log.d(TAG, "UPLOAD ERROR - - ID: $id - - EX:" + ex.toString());
                    }
                })
        }
    }

//    private fun downloadSingle(
//        toRemoteKey: String?
//    ): Completable {
//        Log.d("mmm single url", toRemoteKey.toString())
//        return Completable.create { emitter ->
//            val s3Url: URL = s3Client.getUrl(bucketName, toRemoteKey)
//            arrImage[toRemoteKey.toString()] = s3Url.toString()
//            Log.d("mmm single url", s3Url.toString())
//            emitter.onComplete()
//        }
//    }
}