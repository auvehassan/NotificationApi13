package com.auvehassan.android._13

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.auvehassan.android.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/*Migrate The Deprecated OnBackPressed Function — Android 13*/

/*
Old style

    override fun onBackPressed() {
        //showing dialog and then closing the application..
        showDialog()
    }

    private fun showDialog(){
        MaterialAlertDialogBuilder(this).apply {
            setTitle("are you sure?")
            setMessage("want to close the application ?")
            setPositiveButton("Yes") { _, _ -> finish() }
            setNegativeButton("No", null)
            show()
        }
    }

* */
class OnBackPressedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_back_pressed)
        // adding onbackpressed callback listener.
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            //showing dialog and then closing the application..
            showDialog()
        }
    }


    private fun showDialog() {
        MaterialAlertDialogBuilder(this).apply {
            setTitle("are you sure?")
            setMessage("want to close the application ?")
            setPositiveButton("Yes") { _, _ -> finish() }
            setNegativeButton("No", null)
            show()
        }
    }
}

/*
Step 1 :

To enable the predictive back gesture APIs, set enableOnBackInvokedCallback to true in the manifest.

Step 2: Create a onBackPressed Callback in Activity / Fragment.
OnBackPressedCallback(enabled) //enabled is true or false

In this callback, enabled is a default parameter which we have to pass by any condition if needed.
If we want by default, you can enable it by passing true.

The below code is the total replica of our old way onbackPressed() function.

private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            //showing dialog and then closing the application..
            showDialog()
        }
   }

STEP 3 :

Add the callback in the onBackPressedDispatcher().
This should be inside the onCreate function and it’s a good practice to use the bottom of setContentView or after Binding the layout.

  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // adding onbackpressed callback listener.
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)
    }


Note : If suppose onBackPressedDispatcher or OnBackPressedCallback are not found for you guys, please check your activity ktx version which should be at least or more than 1.6.1.

If you create a new project in Android studio, the above Note is not mandatory.

dependencies { //build.gradle app level
    implementation 'androidx.activity:activity-ktx:1.6.1'
}
**********************************************************************


Now, if you not have something like Dialog box for confirmation in onBackPressed, you can get back-to-home gesture animations , which is in Android 13.

To test it, make onBackPressedCallback isEnabled as false.(This will not show Dialog popup or anything inside the callback function, typically you can add this isEnabled = false under some conditions.)

make onBackPressedCallback isEnabled as false .(Note this will not show Dialog popup.)

And then, Starting with the Android 13 final release, you should be able to enable a developer option to test the back-to-home animation

On your device, go to Settings > System > Developer options.
Select Predictive back animations.
Launch your updated app, and use the back gesture to see it in action.
* */