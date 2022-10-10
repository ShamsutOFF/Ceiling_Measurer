package com.example.hellolibrary

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings

import android.telephony.TelephonyManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.json.JSONObject
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


private const val TAG = "@@@@@ InfoLibrary"

class InfoLibrary {
    fun giveContext(applicationContext: Context) {

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

    fun printSystemInfo2() {
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
        val mapOfDeviceInfo =
            mapOf(
                "MODEL" to Build.MODEL,
                "MANUFACTURER" to Build.MANUFACTURER,
                "BOARD" to Build.BOARD,
                "BRAND" to Build.BRAND,
                "ID" to Build.ID,
                "USER" to Build.USER,
                "VERSION.SDK_INT" to Build.VERSION.SDK_INT
            )
        Log.d(TAG, "mapOfDeviceInfo = $mapOfDeviceInfo")
    }

    fun printContextInfo(applicationContext: Context) {
        Log.d(TAG, "applicationContext = $applicationContext")
        val androidID = Settings.Secure.getString(
            applicationContext.contentResolver,
            Settings.Secure.ANDROID_ID
        )
        Log.d(TAG, "androidID = $androidID")
    }

    fun getDeviceInfo(): Map<String, Any> {
        return mapOf(
            "MODEL" to Build.MODEL,
            "MANUFACTURER" to Build.MANUFACTURER,
            "BOARD" to Build.BOARD,
            "BRAND" to Build.BRAND,
            "ID" to Build.ID,
            "USER" to Build.USER,
            "VERSION.SDK_INT" to Build.VERSION.SDK_INT
        )
    }

//    fun sendInfoToPostman(string: String) {
//        Log.d(TAG, "sendInfoToPostman() called")
//        Thread {
//            val connection = URL("https://42aea87e-bcb4-4f70-b387-7061bee87d95.mock.pstmn.io/orders/1234567").openConnection() as HttpURLConnection
//            connection.requestMethod = "GET"
//            connection.readTimeout = 10_000
//            val reader = BufferedReader(InputStreamReader(connection.inputStream))
//            val response = reader.readLines().joinToString()
//            Log.d(TAG, "response GET = $response")
//        }.start()
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendInfoToPostmanPOST(string: String) {
        Log.d(TAG, "sendInfoToPostmanPOST() called")
        Thread {
            val url = URL("https://42aea87e-bcb4-4f70-b387-7061bee87d95.mock.pstmn.io/orders/habr")
            val connection = url.openConnection() as HttpURLConnection
            try {
                connection.requestMethod = "POST"
                connection.readTimeout = 10_000
                connection.doOutput = true
                connection.setChunkedStreamingMode(0)

                val out = BufferedOutputStream(connection.outputStream)
                out.write(string.toByteArray() + getDeviceInfo().toString().toByteArray())
                out.flush()

                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = reader.readLines().joinToString()
                Log.d(TAG, "response POST = $response")
            } finally {
                connection.disconnect()
            }

        }.start()
    }

    fun rawJSON() {
        Log.d(TAG, "rawJSON() called")
        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("name", "Jack")
        jsonObject.put("salary", "3540")
        jsonObject.put("age", "23")

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        Thread {
            val url = URL("https://42aea87e-bcb4-4f70-b387-7061bee87d95.mock.pstmn.io/orders/habr")
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.setRequestProperty(
                "Content-Type",
                "application/json"
            ) // The format of the content we're sending to the server
            httpURLConnection.setRequestProperty(
                "Accept",
                "application/json"
            ) // The format of response we want to get from the server
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = true

            // Send the JSON we created
            val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)
            outputStreamWriter.write(jsonObjectString)
            outputStreamWriter.flush()

            // Check if the connection is successful
            val responseCode = httpURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {

                val reader = BufferedReader(InputStreamReader(httpURLConnection.inputStream))
                val response = reader.readLines().joinToString()
                Log.d(TAG, "response = $response")

//                val response = httpURLConnection.inputStream.bufferedReader()
//                    .use { it.readText() }  // defaults to UTF-8
//
//                    // Convert raw JSON to pretty JSON using GSON library
//                    val gson = GsonBuilder().setPrettyPrinting().create()
//                    val prettyJson = gson.toJson(JsonParser.parseString(response))
//                    Log.d("Pretty Printed JSON :", prettyJson)

            } else {
                Log.e("HTTPURLCONNECTION_ERROR", responseCode.toString())
            }
        }.start()
    }

    fun sendInfo(jsonObject: JSONObject) {
        Log.d(TAG, "rawJSON() called")
        // Create JSON using JSONObject


        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        Thread {
            val url = URL("https://42aea87e-bcb4-4f70-b387-7061bee87d95.mock.pstmn.io/orders/habr")
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.setRequestProperty(
                "Content-Type",
                "application/json"
            ) // The format of the content we're sending to the server
            httpURLConnection.setRequestProperty(
                "Accept",
                "application/json"
            ) // The format of response we want to get from the server
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = true

            // Send the JSON we created
            val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)
            outputStreamWriter.write(jsonObjectString)
            outputStreamWriter.flush()

            // Check if the connection is successful
            val responseCode = httpURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {

                val reader = BufferedReader(InputStreamReader(httpURLConnection.inputStream))
                val response = reader.readLines().joinToString()
                Log.d(TAG, "response = $response")

//                val response = httpURLConnection.inputStream.bufferedReader()
//                    .use { it.readText() }  // defaults to UTF-8
//
//                    // Convert raw JSON to pretty JSON using GSON library
//                    val gson = GsonBuilder().setPrettyPrinting().create()
//                    val prettyJson = gson.toJson(JsonParser.parseString(response))
//                    Log.d("Pretty Printed JSON :", prettyJson)

            } else {
                Log.e("HTTPURLCONNECTION_ERROR", responseCode.toString())
            }
        }.start()
    }

}