package com.ashhasib.devicebrief.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ashhasib.devicebrief.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout


class DeviceFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_device, container, false)

        setupCollapsingLayout(view)

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
}