package com.ashhasib.devicebrief.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.ashhasib.devicebrief.R
import com.ashhasib.devicebrief.infogenerators.DeviceInfoGenerator
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.fragment_device.*
import net.cachapa.expandablelayout.ExpandableLayout


class DeviceFragment: Fragment() {

    val TAG = "DeviceFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_device, container, false)

        setupCollapsingLayout(view)


        setupExpandsForDevice(view)
        setupExpandsForOs(view)
        setupExpandsForSensors(view)
        viewInfo(view)


        return view
    }




    fun setupCollapsingLayout(view: View) {
        val collapsibleLayout = view.findViewById(R.id.collapsibleLayout) as CollapsingToolbarLayout
        val appBar = view.findViewById(R.id.appBar) as AppBarLayout
        appBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = false
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsibleLayout.title  = "Device"
                    isShow = true
                } else if (isShow) {
                    collapsibleLayout.title = ""
                    isShow = false
                }
            }
        })

    }


    fun setupExpandsForDevice(view: View) {
        val deviceInfo = view.findViewById(R.id.cardDeviceInfo) as CardView
        val imgDeviceInfo = view.findViewById(R.id.imgDeviceInfo) as ImageView

        val expandableDeviceInfo = view.findViewById(R.id.expandableDeviceInformation) as ExpandableLayout
        expandableDeviceInfo.collapse()

        deviceInfo.setOnClickListener {
            if(expandableDeviceInfo.visibility == View.GONE) {
                expandableDeviceInfo.expand()
                imgDeviceInfo.setImageResource(R.drawable.ic_up_arrow_black)
            }
            else {
                expandableDeviceInfo.collapse()
                imgDeviceInfo.setImageResource(R.drawable.ic_down_arrow_black)
            }
        }
    }



    fun setupExpandsForOs(view: View) {
        val cardOsInfo = view.findViewById(R.id.cardOSInfo) as CardView
        val imgOsInfo = view.findViewById(R.id.imgOSInfo) as ImageView

        val expandableOsInformation = view.findViewById(R.id.expandableOSInformation) as ExpandableLayout
        expandableOsInformation.collapse()

        cardOsInfo.setOnClickListener{
            if(expandableOsInformation.visibility == View.GONE) {
                expandableOsInformation.expand()
                imgOsInfo.setImageResource(R.drawable.ic_up_arrow_black)
            }
            else {
                expandableOsInformation.collapse()
                imgOsInfo.setImageResource(R.drawable.ic_down_arrow_black)
            }
        }
    }

    fun setupExpandsForSensors (view: View) {
        val cardSensorInfo = view.findViewById(R.id.cardSensorInfo) as CardView
        val imgSensorInfo = view.findViewById(R.id.imgSensorsInfo) as ImageView

        val expandableSensorInformation = view.findViewById(R.id.expandableSensorInformation) as ExpandableLayout
        expandableSensorInformation.collapse()

        cardSensorInfo.setOnClickListener {
            if(expandableSensorInformation.visibility==View.GONE) {
                expandableSensorInformation.expand()
                imgSensorInfo.setImageResource(R.drawable.ic_up_arrow_black)
            }
            else {
                expandableSensorInformation.collapse()
                imgSensorInfo.setImageResource(R.drawable.ic_down_arrow_black)
            }
        }
    }




    fun viewInfo(view: View) {

        val details = DeviceInfoGenerator(activity!!,context!!)


        val txtDeviceName = view.findViewById(R.id.txtDeviceName) as TextView
        val txtBrand = view.findViewById(R.id.txtBrand) as TextView
        val txtModel = view.findViewById(R.id.txtModel) as TextView
        val txtResolution = view.findViewById(R.id.txtResolution) as TextView
        val txtDensity = view.findViewById(R.id.txtDensity) as TextView
        val screenResolution = details.getScreenResolution()
        val density = details.getDensity()

        val txtOsName = view.findViewById(R.id.txtOsName) as TextView
        val txtOsVersion = view.findViewById(R.id.txtOsVersion) as TextView
        val txtApiLevel = view.findViewById(R.id.txtApiLevel) as TextView
        val txtOsFirstInstalled = view.findViewById(R.id.txtOsFirstInstalled) as TextView
        val txtOsLastUpdated = view.findViewById(R.id.txtOsLastUpdated) as TextView

        val txtSensorList = view.findViewById(R.id.txtSensorsList) as TextView


        txtDeviceName.setText(details.getDeviceName())
        txtBrand.setText("Brand: ${details.getDeviceBrand()}")
        txtModel.setText("Model: ${details.getDeviceModel()}")
        txtResolution.setText("Resolution: ${screenResolution}")
        txtDensity.setText("Density: ${density}")

        txtOsName.setText("Name: ${details.getOSName()}")
        txtOsVersion.setText("Version: ${details.getVersionRelease()}")
        txtApiLevel.setText("API Level: ${details.getApiLevel()}")
        txtOsFirstInstalled.setText("First Installed: ${details.getOsFirstInstallDate()}")
        txtOsLastUpdated.setText("Last Updated: ${details.getOsLastUpdateTime()}")

        txtSensorList.setText("Sensors:\n ${details.getSensorsInfo()}")

    }
}