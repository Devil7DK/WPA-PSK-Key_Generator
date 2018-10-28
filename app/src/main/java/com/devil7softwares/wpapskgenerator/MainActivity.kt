package com.devil7softwares.wpapskgenerator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generate = findViewById<Button>(R.id.btnGeneratePSK)
        generate.setOnClickListener(generatePSK)
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

    private val generatePSK = View.OnClickListener {
        val id = findViewById<EditText>(R.id.txtSSID)
        val pass = findViewById<EditText>(R.id.txtPassPhrase)
        val psk = findViewById<EditText>(R.id.txtPSK)

        val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val keySpec = PBEKeySpec(pass.text.toString().toCharArray(), id.text.toString().toByteArray(), 4096, 256)
        val secretKey = secretKeyFactory.generateSecret(keySpec)
        val bytes = secretKey.encoded

        val list = ArrayList<Byte>(bytes.size)
        for (b in bytes) {
            list.add(b)
        }

        psk.setText(stringToHex(list))
    }
}
