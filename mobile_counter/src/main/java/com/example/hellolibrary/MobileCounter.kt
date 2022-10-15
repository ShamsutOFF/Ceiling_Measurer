package com.example.hellolibrary

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.room.Room
import com.example.hellolibrary.data.MessageDatabase
import com.example.hellolibrary.data.dao.MessageDao
import com.example.hellolibrary.data.entities.Message
import org.json.JSONObject
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

private const val ENDPOINT =
    "https://42aea87e-bcb4-4f70-b387-7061bee87d95.mock.pstmn.io/orders/habr"

class MobileCounter {
    private lateinit var dao: MessageDao
    private var formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    @SuppressLint("HardwareIds")
    fun init(context: Context) {
        androidID = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
        val db =
            Room.databaseBuilder(context, MessageDatabase::class.java, "Message_database").build()
        dao = db.messageDao()
        incrementLaunchCounter(context)
        sendInfo(JSONObject().put("event", "App Started!"))
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
        with(json) {
            put("launchCounter", launchCounter)
            put("androidID", androidID)
            put("MODEL", Build.MODEL)
            put("MANUFACTURER", Build.MANUFACTURER)
            put("BOARD", Build.BOARD)
            put("BRAND", Build.BRAND)
            put("ID", Build.ID)
            put("USER", Build.USER)
            put("VERSION.SDK_INT", Build.VERSION.SDK_INT)
        }
        return json
    }

    fun sendInfo(jsonObjectForSending: JSONObject) {
        val formattedDate = formatter.format(Date())
        jsonObjectForSending.put("Date", formattedDate)
        Thread {
            val httpURLConnection = openHttpURLConnection()
            try {
                sendJsonToEndpoint(httpURLConnection, jsonObjectForSending)
                if (httpURLConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    val json = httpURLConnection.inputStream.bufferedReader().readText()
                    dao.deleteAll()
                    Log.d(TAG, "responseCode = ${httpURLConnection.responseCode}")
                    Log.d(TAG, "json = $json")
                } else {
                    val message = Message(0, jsonObjectForSending.toString())
                    dao.insertMessage(message)
                    Log.e("HTTPURLCONNECTION_ERROR", httpURLConnection.responseCode.toString())
                }
            } catch (ex: Exception) {
                val message = Message(0, jsonObjectForSending.toString())
                dao.insertMessage(message)
                ex.printStackTrace()
            } finally {
                httpURLConnection.disconnect()
            }
        }.start()
    }

    private fun openHttpURLConnection(): HttpURLConnection {
        Log.d(TAG, "openHttpURLConnection() called")
        val httpURLConnection = URL(ENDPOINT).openConnection() as HttpURLConnection
        with(httpURLConnection) {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json")
            setRequestProperty("Accept", "application/json")
            doInput = true
            doOutput = true
        }
        return httpURLConnection
    }

    private fun sendJsonToEndpoint(
        httpURLConnection: HttpURLConnection,
        jsonObjectForSending: JSONObject
    ) {
        val jsonForSending = JSONObject()
        jsonForSending.accumulate("DeviceInfo", getDeviceInfoJSON())

        val messagesList = dao.getAll()
        messagesList.forEach {
            jsonForSending.accumulate("Action", it.message)
        }
        jsonForSending.accumulate("Action", jsonObjectForSending)
        Log.d(TAG, "sendInfo() called jsonForSending = $jsonForSending")
        val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)
        outputStreamWriter.write(jsonForSending.toString())
        outputStreamWriter.flush()
    }
}