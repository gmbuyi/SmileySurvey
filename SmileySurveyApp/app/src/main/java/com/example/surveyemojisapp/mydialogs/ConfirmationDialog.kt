package com.example.surveyemojisapp.mydialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.decathlon.sasdec.database.SurveyDatabase
import com.example.surveyemojisapp.R
import com.example.surveyemojisapp.model.Reasons
import com.example.surveyemojisapp.model.Survey
import kotlinx.android.synthetic.main.button_dialog.*
import java.util.concurrent.Executors

class ConfirmationDialog(context: Context, private val database : SurveyDatabase,
                          val survey : Survey, val selectedList : List<String>) : Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.confirmation_dialog)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        confirm_button.setOnClickListener {
            val executor = Executors.newSingleThreadExecutor()
            executor.execute {

                val surveyId = database.surveyDao().insert(survey)
                for (reason in selectedList) {
                    database.raisonsDao()
                        .insert(Reasons(surveyId.toInt(), reason))

                }
            }
            cancel()
            FinalDialog(context).show()


        }

        cancel_button.setOnClickListener{

            cancel()
        }

    }





}