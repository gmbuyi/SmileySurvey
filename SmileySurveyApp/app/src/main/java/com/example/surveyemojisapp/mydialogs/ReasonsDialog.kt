package com.example.surveyemojisapp.mydialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import com.decathlon.sasdec.database.SurveyDatabase
import com.example.surveyemojisapp.R
import com.example.surveyemojisapp.model.Survey
import kotlinx.android.synthetic.main.button_dialog.*
import kotlinx.android.synthetic.main.reasons_layout.*

class ReasonsDialog( context: Context, private val database : SurveyDatabase,
                    val survey : Survey, private val listOfReason : Array<String?>):Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.reasons_layout)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        confirm_button.visibility= View.INVISIBLE

        selectedList = ArrayList()

        for (reason in listOfReason){
            val checkbox = CheckBox(context)
            checkbox.setOnCheckedChangeListener(MyCheckedChangedListener(confirm_button))
            checkbox.text = reason?.uppercase()
            checkbox.setTypeface(checkbox.typeface, Typeface.BOLD_ITALIC)

            main_layout.addView(checkbox)
        }

        cancel_button.setOnClickListener {  cancel() }
        confirm_button.setOnClickListener {  ConfirmationDialog(context,database,survey,selectedList).show()
            cancel() }



    }



    private class MyCheckedChangedListener (var button : Button): CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(p0: CompoundButton, p1: Boolean) {
            if (p1) {
                selectedList.add(p0.text.toString())
            } else {
                selectedList.remove(p0.text.toString())
            }

            if (selectedList.isEmpty()) button.visibility = View.INVISIBLE else button.visibility = View.VISIBLE


        }


    }

    companion object{
        lateinit var selectedList:ArrayList<String>
    }
}