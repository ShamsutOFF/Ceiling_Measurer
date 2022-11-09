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
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "@@@@@ Mobile Counter"
private var deviceID = ""
private var appName = ""
private var launchCounter = 0
private const val SAVED_DATA = "SavedData"
private const val COUNTER = "Counter"

private const val ENDPOINT_POSTMAN =
    "https://42aea87e-bcb4-4f70-b387-7061bee87d95.mock.pstmn.io/orders/habr2"
private const val ENDPOINT =
    "https://stats.mos.ru/eds.gif?o="
private const val ENDPOINT_TEST =
    "https://stats.mos.ru/eds.gif?o=%7B%22eventType%22%3A%22mobile%22%2C%22eventSrc%22%3A%22Ceiling%20Measurer%22%2C%22eventDst%22%3A%22stats%22%2C%22eventTime%22%3A%201666867216%2C%22sso_id%22%3A%229180f787-65e3-11ed-bdc3-0242ac120002%22%2C%23eventObject%22%3A%20%7B%22launchCounter%22%3A%2025%2C%22deviceID%22%3A%22145feb91c6e32636%22%2C%22model%22%3A%22Android%20SDK%20built%20for%20x86%22%2C%22manufacturer%22%3A%22Google%22%2C%22board%22%3A%22goldfish_x86%22%2C%22brand%22%3A%22google%22%2C%22id%22%3A%22QSR1.210802.001%22%2C%22user%22%3A%22android-build%22%2C%22version.sdk_int%22%3A%2029%7D%7D"

class MobileCounter {
    private lateinit var dao: MessageDao
    private var formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    @SuppressLint("HardwareIds")
    fun init(context: Context) {
        val applicationInfo = context.applicationInfo
        val stringId = applicationInfo.labelRes
        appName =
            if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(
                stringId
            )
        deviceID = Settings.Secure.getString(
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
        editor.putInt(COUNTER, launchCounter).apply()
    }

    private fun getDeviceInfoJSON(): JSONObject {
        val json = JSONObject()
        with(json) {
            put("launch_counter", launchCounter)
            put("device_id", deviceID)
            put("model", Build.MODEL)
            put("manufacturer", Build.MANUFACTURER)
            put("board", Build.BOARD)
            put("brand", Build.BRAND)
            put("id", Build.ID)
            put("user", Build.USER)
            put("version.sdk_int", Build.VERSION.SDK_INT)
        }
        return json
    }

    fun sendInfo(jsonObjectForSending: JSONObject) {
        Thread {
            val encodedDataString = getEncodedJsonAsString(jsonObjectForSending)
            val httpURLConnection =
                URL(ENDPOINT + encodedDataString).openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "GET"
            try {
                if (httpURLConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "responseCode = ${httpURLConnection.responseCode}")
                    checkInfoInBD()
                } else {
                    dao.insertMessage(Message(0, encodedDataString))
                    Log.e("INTERCONNECTION_ERROR", httpURLConnection.responseCode.toString())
                }
            } catch (ex: Exception) {
                dao.insertMessage(Message(0, encodedDataString))
                ex.printStackTrace()
            } finally {
                httpURLConnection.disconnect()
            }
        }.start()
    }

    private fun sendInfoFromBD(encodedDataString: String) {
        Log.d(TAG, "sendInfoFromBD() called with: encodedDataString = $encodedDataString")
        Thread {
            val url = URL(ENDPOINT + encodedDataString)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "GET"
            try {
                if (httpURLConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "responseCode = ${httpURLConnection.responseCode}")
                    dao.deleteMessage(encodedDataString)
                    checkInfoInBD()
                } else {
                    Log.e("INTERCONNECTION_ERROR", httpURLConnection.responseCode.toString())
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                httpURLConnection.disconnect()
            }
        }.start()
    }

    private fun checkInfoInBD() {
        Log.d(TAG, "checkInfoInBD() called")
        val encodedStringForSending = dao.getFirst()?.message
        if (encodedStringForSending.isNullOrEmpty()) {
            Log.d(TAG, "We got nothing to sending!!!")
            return
        } else {
            sendInfoFromBD(encodedStringForSending)
        }
    }

    private fun getEncodedJsonAsString(jsonObjectForSending: JSONObject): String {
        val jsonForSending = JSONObject()
        with(jsonForSending) {
            put("eventType", "mobile")
            put("eventSrc", appName)
            put("eventDst", "stats")
            put("eventTime", Date().time)
            put("sso_id", UUID.randomUUID().toString())
        }

        val eventObject = getDeviceInfoJSON()
        val keys = jsonObjectForSending.keys()

        while (keys.hasNext()) {
            val tempKey = keys.next()
            Log.d(TAG, "tempKey = $tempKey")
            eventObject.put(tempKey + "_app", jsonObjectForSending.get(tempKey))
        }
        jsonForSending.put("eventObject", eventObject)
        Log.d(TAG, "@@@ getEncodedJsonAsString jsonForSending = $jsonForSending")

        return URLEncoder.encode(jsonForSending.toString(), "UTF-8")
    }
}