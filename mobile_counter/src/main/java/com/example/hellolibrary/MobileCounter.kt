package com.example.hellolibrary

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "@@@@@ Mobile Counter"
private var androidID = ""
private var launchCounter = 0
private const val SAVED_DATA = "SavedData"
private const val COUNTER = "Counter"

private const val ENDPOINT = "https://42aea87e-bcb4-4f70-b387-7061bee87d95.mock.pstmn.io/orders/habr"

class MobileCounter {

    private var calendar: Calendar = Calendar.getInstance()
    private var formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private var formattedDate: String =""

    fun init(context: Context) {
        androidID = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
        incrementLaunchCounter(context)
        val event = JSONObject()
        event.put("event", "App Started!")
        sendInfo(event)
    }

    private fun incrementLaunchCounter(context: Context) {
        val sharedPref = context.getSharedPreferences(SAVED_DATA, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        launchCounter = sharedPref.getInt(COUNTER, 0) + 1
        Log.d(TAG, "incrementLaunchCounter() launchCounter = $launchCounter")
        editor.putInt(COUNTER, launchCounter).apply()
    }

    private fun getDeviceInfoJSON(): JSONObject {
        val json = JSONObject()
        json.put("launchCounter", launchCounter)
        json.put("androidID", androidID)
        json.put("MODEL", Build.MODEL)
        json.put("MANUFACTURER", Build.MANUFACTURER)
        json.put("BOARD", Build.BOARD)
        json.put("BRAND", Build.BRAND)
        json.put("ID", Build.ID)
        json.put("USER", Build.USER)
        json.put("VERSION.SDK_INT", Build.VERSION.SDK_INT)
        return json
    }

    fun sendInfo(jsonObjectForSending: JSONObject) {
        Log.d(TAG, "sendInfo() called this =$this")
        formattedDate = formatter.format(calendar.time)
        jsonObjectForSending.put("Date", formattedDate)
        Thread {
            val url = URL(ENDPOINT)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            try {
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.setRequestProperty(
                    "Content-Type",
                    "application/json"
                )
                httpURLConnection.setRequestProperty(
                    "Accept",
                    "application/json"
                )
                httpURLConnection.doInput = true
                httpURLConnection.doOutput = true

                val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)

                val jsonForSending = JSONObject()
                jsonForSending.put("DeviceInfo", getDeviceInfoJSON())
                jsonForSending.put("Action", jsonObjectForSending)
                outputStreamWriter.write(jsonForSending.toString())
                Log.d(TAG, "sendInfo() called jsonForSending = $jsonForSending")
                outputStreamWriter.flush()

                if (httpURLConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    val json = httpURLConnection.inputStream.bufferedReader().readText()
                    val reader = BufferedReader(InputStreamReader(httpURLConnection.inputStream))
                    Log.d(TAG, "responseCode = ${httpURLConnection.responseCode}")
                    Log.d(TAG, "json = $json")
                } else {
                    // TODO: Здесь нужно сохранять jsonObjectForSending если отправка не удалась
                    Log.e("HTTPURLCONNECTION_ERROR", httpURLConnection.responseCode.toString())
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                httpURLConnection.disconnect()
            }
        }.start()
    }
}