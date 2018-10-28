package com.devil7softwares.wpapskgenerator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun stringToHex(str: ArrayList<Byte>) =
            str
                    .toByteArray()
                    .joinToString(separator = "") {
                        it
                                .toInt()
                                .and(0xff)
                                .toString(16)
                                .padStart(2, '0')
                    }
    
}
