package com.tempestronics.inheritancebug

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableMap

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "InheritanceBug"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playersList = ObservableLinkedHashMap<String, Int>()
        playersList.addOnMapChangedCallback(object : ObservableMap.OnMapChangedCallback<ObservableLinkedHashMap<String, Int>, String, Int>() {
            override fun onMapChanged(sender: ObservableLinkedHashMap<String, Int>?, key: String?) {
                Log.i(TAG, "Player List changed")
            }
        })

        playersList.putAll(mapOf(
            "Andy" to 5,
            "Betty" to 7,
            "Cathy" to 15,
            "Dave" to 14
        ))
    }
}