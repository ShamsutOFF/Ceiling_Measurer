package com.example.hellolibrary

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build

import android.telephony.TelephonyManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*


private const val TAG = "### HelloLibrary"

class HelloLibrary : AppCompatActivity() {

    fun helloFromOurLibrary() {
        println("### Hello World! This is our new Android library!")
        println("### DeviceName: " + getDeviceName())
//        println("### android_id: $android_id")
    }

    fun getDeviceName(): String? {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.lowercase(Locale.getDefault())
                .startsWith(manufacturer.lowercase(Locale.getDefault()))
        ) {
            capitalize(model)
        } else {
            capitalize(manufacturer) + " " + model
        }
    }


    private fun capitalize(s: String?): String {
        if (s == null || s.length == 0) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            first.uppercaseChar().toString() + s.substring(1)
        }
    }

    fun getSystemInfo() {
        Log.d(TAG, "getSystemInfo() called")
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                applicationContext.checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
            }
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d(TAG, "checkSelfPermission != PackageManager.PERMISSION_GRANTED")
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.READ_PHONE_STATE
                    )
                ) {
                    //TODO
                    Log.d(TAG, "shouldShowRequestPermissionRationale")
                } else {
                    Log.d(TAG, "requestPermissions")
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_PHONE_STATE),
                        2
                    )
                }
            }
            var line1num = ""
            var phoneCount = ""
            var phoneType = ""
            var deviceSoftwareVersion = ""
            Log.d(TAG, "checkSelfPermission PERMISSION_GRANTED")
            val tm = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
            val allCellInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                tm.allCellInfo
                deviceSoftwareVersion = tm.deviceSoftwareVersion.toString()
                line1num = tm.line1Number
                phoneCount = tm.phoneCount.toString()
                phoneType = tm.phoneType.toString()
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            if (allCellInfo != null)
                println("$TAG allCellInfo  $allCellInfo")
            println("$TAG line1num  $line1num")
            println("$TAG phoneCount  $phoneCount")
            println("$TAG phoneType  $phoneType")
            println("$TAG deviceSoftwareVersion  $deviceSoftwareVersion")

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun printSystemInfo2(){
        Log.d(TAG, "printSystemInfo2() called")
        Log.d(TAG, "Build.MODEL ${Build.MODEL}")
        Log.d(TAG, "Build.MANUFACTURER ${Build.MANUFACTURER}")
        Log.d(TAG, "Build.BOARD ${Build.BOARD}")
        Log.d(TAG, "Build.BRAND ${Build.BRAND}")
        Log.d(TAG, "Build.DISPLAY ${Build.DISPLAY}")
        Log.d(TAG, "Build.FINGERPRINT ${Build.FINGERPRINT}")
        Log.d(TAG, "Build.HARDWARE ${Build.HARDWARE}")
        Log.d(TAG, "Build.ID ${Build.ID}")
        Log.d(TAG, "Build.PRODUCT ${Build.PRODUCT}")
        Log.d(TAG, "Build.USER ${Build.USER}")
        Log.d(TAG, "Build.SERIAL ${Build.SERIAL}")
        Log.d(TAG, "Build.VERSION.SDK_INT ${Build.VERSION.SDK_INT}")

        val s = this.parent
        Log.d(TAG, "this ${this}")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d(TAG, "this.isUiContext ${this.isUiContext}")
        }
//        Log.d(TAG, "applicationContext ${applicationContext}")

    }
}