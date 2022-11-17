package com.auvehassan.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.auvehassan.android._13.MainActivity13

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        findViewById<Button>(R.id.buttonAndroid12).setOnClickListener{startActivity(Intent(this, MainActivity1::class.java))}
        findViewById<Button>(R.id.buttonAndroid13).setOnClickListener{startActivity(Intent(this, MainActivity13::class.java))}

    }
}