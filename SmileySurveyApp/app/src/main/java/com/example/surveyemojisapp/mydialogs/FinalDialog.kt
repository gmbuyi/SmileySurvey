package com.example.surveyemojisapp.mydialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Window
import com.example.surveyemojisapp.R
import kotlinx.android.synthetic.main.final_dialog.*

class FinalDialog( context: Context): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.final_dialog)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setOnShowListener {

            val  time : Long = 5000

            object : CountDownTimer(time, 1000) {
                override fun onFinish() {
                    if (isShowing){
                        dismiss()
                    }

                }

                override fun onTick(p0: Long) {
                    var text = "seconds"
                    val timeInSecond = p0/1000;
                    if (timeInSecond < 2) text = "second"
                    timer_text.text = "Windows will close in $timeInSecond $text"

                }
            }
                .start()

        }

    }


}