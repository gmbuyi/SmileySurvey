package com.example.surveyemojisapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MySetting: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preference)


        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame,PreferencesSettings())
            .commit()





    }
}