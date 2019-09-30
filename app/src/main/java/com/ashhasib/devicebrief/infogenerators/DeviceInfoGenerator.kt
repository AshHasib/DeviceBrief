package com.ashhasib.devicebrief.infogenerators

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Point
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Build
import android.util.Log
import java.security.AccessControlContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class DeviceInfoGenerator(val activity: Activity, val context: Context) {


    /*
    fun getDeviceName () :String {
        return Build.DEVICE.toString()
    }
    */

    val osNameMap : HashMap<Int, String> = HashMap<Int, String>()

    fun loadOSNamesMap() {
        osNameMap.put(9,"Gingerbread")
        osNameMap.put(10,"Gingerbread")
        osNameMap.put(11,"Honeycomb")
        osNameMap.put(12,"Honeycomb")
        osNameMap.put(13,"Honeycomb")
        osNameMap.put(14,"Ice Cream Sandwitch")
        osNameMap.put(15,"Ice Cream Sandwitch")
        osNameMap.put(16,"Jelly Bean")
        osNameMap.put(17,"Jelly Bean")
        osNameMap.put(18,"Jelly Bean")
        osNameMap.put(19,"KitKat")
        osNameMap.put(20,"KitKat")
        osNameMap.put(21,"Lollipop")
        osNameMap.put(22,"Lollipop")
        osNameMap.put(23,"Marshmallow")
        osNameMap.put(24,"Noughat")
        osNameMap.put(25,"Noughat")
        osNameMap.put(26,"Oreo")
        osNameMap.put(27,"Oreo")
        osNameMap.put(28,"Pie")
        osNameMap.put(29,"Android 10")
    }


    fun getDeviceName(): String? {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) capitalizePhrase(model) else capitalizePhrase(
            manufacturer
        ) + " " + model
    }

    private fun capitalizePhrase(s: String?): String? {
        if (s == null || s.length == 0)
            return s
        else {
            val phrase = StringBuilder()
            var next = true
            for (c in s.toCharArray()) {
                if (next && Character.isLetter(c) || Character.isWhitespace(c))
                    next = Character.isWhitespace(Character.toUpperCase(c))
                phrase.append(c)
            }
            return phrase.toString()
        }
    }






    fun getDeviceBrand() :String {
        return Build.BRAND.toString()
    }







    fun getDeviceModel () : String {
        return Build.MODEL.toString()
    }








    fun getScreenResolution() : String {
        val display = activity!!.windowManager.defaultDisplay
        val point = Point()

        display.getSize(point)

        return "${point.x} x ${point.y}"
    }







    fun getDensity() : String {
        val density = context.resources.displayMetrics.density

        if (density >= 4.0) {
            return "xxxhdpi";
        }
        if (density >= 3.0) {
            return "xxhdpi";
        }
        if (density >= 2.0) {
            return "xhdpi";
        }
        if (density >= 1.5) {
            return "hdpi";
        }
        if (density >= 1.0) {
            return "mdpi";
        }
        return "ldpi";
    }




    fun getOSName() :String {

        loadOSNamesMap()
        val api_level = Build.VERSION.SDK_INT
        val osName = osNameMap.get(api_level)

        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName,0)
            val version = pInfo.versionName

            Log.d("VERSION:",version.toString())
            Log.d("VERSION:",version)


        }catch (e:PackageManager.NameNotFoundException){
            e.printStackTrace()
        }

        return osName.toString()
    }

    fun getVersionRelease(): String {
        return Build.VERSION.RELEASE
    }

    fun getApiLevel(): String{
        return Build.VERSION.SDK_INT.toString()
    }


    fun getOsFirstInstallDate(): String {

        val longInstalledTime = context.packageManager.getPackageInfo(context.packageName,0).firstInstallTime

        val dateFormat = SimpleDateFormat("EEE, MMM d, yyyy h:mm a")

        val calendar = Calendar.getInstance()

        calendar.timeInMillis = longInstalledTime

        return dateFormat.format(calendar.time)
    }

    fun getOsLastUpdateTime(): String {

        val longInstalledTime = context.packageManager.getPackageInfo(context.packageName,0).lastUpdateTime

        val dateFormat = SimpleDateFormat("EEE, MMM d, yyyy h:mm a")

        val calendar = Calendar.getInstance()

        calendar.timeInMillis = longInstalledTime

        return dateFormat.format(calendar.time)
    }



    fun getSensorsInfo(): String {
        val sensorManager = activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val listSensor = sensorManager.getSensorList(Sensor.TYPE_ALL)

        var listString = ""

        for(s in listSensor) {
            listString+="- ${s.name}\n"
        }
        return listString
    }





}