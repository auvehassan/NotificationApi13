package com.auvehassan.android.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.auvehassan.android.R
import com.google.android.play.core.review.ReviewManagerFactory

class InAppReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_app_review)
        val manager = ReviewManagerFactory.create(this)
        val request = manager.requestReviewFlow()

        request.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                Log.d(Companion.TAG, "onCreate: ${request.isSuccessful}")
                val reviewInfo = request.result
                val flow = manager.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener { _ ->
                    //Continue your application process
                }
            } else {
                Log.d(Companion.TAG, "onCreate: ${request.exception}")
                //Handle the error here
            }
        }
    }

    companion object {
        private const val TAG = "InAppReviewActivity"
    }
}


/*
step01: build.gradle
implementation 'com.google.android.play:core:1.8.2'
implementation 'com.google.android.play:core-ktx:1.8.1'

step02: create the instance of ReviewManager

step03: request the flow to launch the In-App review flow.

step03: check if the request was successful in its onCompleteListener

step04: get the reviewInfo object

step05: Inside the addOnCompleteListener, just keep the application's flow running irrespective of what the results are

step06: Upload your application in the internal test track and test it out.
        Upload your application in internal app sharing and test the review flow.
        Now, while testing it in test cases to check if the review flow is completed and the app's flow is executing in the expected flow then we used FakeReviewManager in place of ReviewManagerFactory like,
* */