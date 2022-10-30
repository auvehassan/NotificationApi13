package com.auvehassan.android13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.buttonNotification).setOnClickListener{startActivity(Intent(this, NotificationActivity::class.java))}
        findViewById<Button>(R.id.buttonCopyPaste).setOnClickListener{startActivity(Intent(this, CopyPasteActivity::class.java))}
    }
}