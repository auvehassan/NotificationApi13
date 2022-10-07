package com.auvehassan.notification13

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE: Int = 1212

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnNotification).setOnClickListener {
            if (!checkPermission()) requestPermission() else showNotification()
        }
    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(applicationContext, POST_NOTIFICATIONS)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(POST_NOTIFICATIONS),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.isNotEmpty()) {
                val permissionAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED

                if (permissionAccepted) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show()
                    showNotification()
                } else {
                    Toast.makeText(
                        this,
                        "Permission Denied, Allow to test notification.",
                        Toast.LENGTH_LONG
                    ).show()

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(POST_NOTIFICATIONS)) {
                            showMessageOKCancel("You need to allow the permission",
                                DialogInterface.OnClickListener { _, _ ->
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(
                                            arrayOf(POST_NOTIFICATIONS),
                                            PERMISSION_REQUEST_CODE
                                        )
                                    }
                                }
                            )
                            return
                        }
                    }
                }
            }
        }
    }

    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this@MainActivity)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    private fun showNotification() {
        val notification = NotificationCompat.Builder(applicationContext, "notificationChannelID")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Notification Title")
            .setContentText("Test notification for Android 13 devices")
            .build()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }
}