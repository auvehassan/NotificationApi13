package com.auvehassan.android.common

import android.Manifest.permission.*
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.auvehassan.android.R


class PermissionApiActivity : AppCompatActivity() {

    private var permissionsList: ArrayList<String> = ArrayList()
    private var permissionsStr = arrayOf(
        CAMERA,
        RECORD_AUDIO,
        READ_EXTERNAL_STORAGE
        )
    var permissionsCount = 0

    @RequiresApi(Build.VERSION_CODES.M)
    var permissionsLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            val list: ArrayList<Boolean> = ArrayList(result.values)
            permissionsList = ArrayList()
            permissionsCount = 0
            for (i in 0 until list.size) {
                if (shouldShowRequestPermissionRationale(permissionsStr[i])) {
                    permissionsList.add(permissionsStr[i])
                } else if (!hasPermission(this, permissionsStr[i])) {
                    permissionsCount++
                }
            }
            if (permissionsList.size > 0) {
                //Some permissions are denied and can be asked again.
                askForPermissions(permissionsList)
            } else if (permissionsCount > 0) {
                //Show alert dialog
                showPermissionDialog()
            } else {
                //All permissions granted. Do your stuff 🤞
                Toast.makeText(this, "All permissions are granted!", Toast.LENGTH_SHORT).show()
            }
        }


    private fun hasPermission(context: Context, permissionStr: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permissionStr
        ) == PackageManager.PERMISSION_GRANTED
    }

    var alertDialog: AlertDialog? = null
    private fun showPermissionDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Permission required")
            .setMessage("Some permissions are need to be allowed to use this app without any problems.")
            .setPositiveButton("Settings") { dialog, _ -> dialog.dismiss() }
        if (alertDialog == null) {
            alertDialog = builder.create()
            if (alertDialog?.isShowing == false) {
                alertDialog?.show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun askForPermissions(permissionsList: ArrayList<String>) {
        val newPermissionStr = arrayOf<String>(permissionsList.size)//todo later
        for (i in newPermissionStr.indices) {
            newPermissionStr[i] = permissionsList[i]
        }
        if (newPermissionStr.isNotEmpty()) {
            permissionsLauncher.launch(newPermissionStr)
        } else {
            /* User has pressed 'Deny & Don't ask again' so we have to show the enable permissions dialog
            which will lead them to app details page to enable permissions from there. */
            showPermissionDialog()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission_api)

        permissionsList = ArrayList()
        permissionsList?.addAll(permissionsStr)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            askForPermissions(permissionsList?:ArrayList())
        }
    }

}


/*
## ActivityResultLauncher API:

1. Defining the ActivityResultLauncher and permissions array

    Now, pass the string array of permissions in the ActivityResultLauncher,
    But also have to keep in mind that if user denies permission once and
    if the the app can ask permission again via shouldShowRequestPermissionRationale or not.
    So we will make a method to ask for permissions in which we will pass the list of permissions as an argument
    so we don’t have to check and pass the permissions list everytime.

2. Asking for permissions
* */