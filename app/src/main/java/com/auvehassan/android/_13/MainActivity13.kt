package com.auvehassan.android._13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.auvehassan.android.R
class MainActivity13 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_13)
        findViewById<Button>(R.id.buttonNotification).setOnClickListener{startActivity(Intent(this, NotificationActivity::class.java))}
        findViewById<Button>(R.id.buttonCopyPaste).setOnClickListener{startActivity(Intent(this, CopyPasteActivity::class.java))}
        findViewById<Button>(R.id.buttonPhotoPicker).setOnClickListener{startActivity(Intent(this, PhotoPickerActivity::class.java))}
        findViewById<Button>(R.id.buttonRevokePermission).setOnClickListener{startActivity(Intent(this, RevokePermission::class.java))}
    }
}