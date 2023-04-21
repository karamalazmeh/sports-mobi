package com.karamalazmeh.sportsmobi.view.util

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter

class SpinnerListAdapter(context: Context) : ArrayAdapter<String>(
    context,
    android.R.layout.simple_spinner_item,
    mutableListOf()) {
    init {
        this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}