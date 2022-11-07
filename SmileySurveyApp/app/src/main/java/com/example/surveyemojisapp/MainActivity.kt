package com.example.surveyemojisapp



import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.preference.PreferenceManager
import com.decathlon.sasdec.database.SurveyDatabase
import com.example.surveyemojisapp.model.Survey
import com.example.surveyemojisapp.mydialogs.ConfirmationDialog
import com.example.surveyemojisapp.mydialogs.MyProgressDialog
import com.example.surveyemojisapp.mydialogs.ReasonsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fab_layout.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermission()
        actionBar?.hide()
        hideSystemUI()
        setContentView(R.layout.activity_main)

        prf= PreferenceManager.getDefaultSharedPreferences(this)
        isOpen =false

        val formatter = SimpleDateFormat("yy/MM/dd_hh:mm:ss")

        val database = SurveyDatabase.getInstance(this)

        disSatisfied.setOnClickListener {

            val survey = Survey("DisSatisfied",formatter.format(Calendar.getInstance().time))

            if (prf.getBoolean("reason_switch",false)){
                 val listReasons =  arrayOf(prf.getString(listKey[0],""),prf.getString(listKey[1],""),prf.getString(listKey[2],""))
                 ReasonsDialog(this,database,survey,listReasons).show()
              } else {
                  ConfirmationDialog(this,database,survey,ArrayList()).show()

            }


        }

        satisfied_alt.setOnClickListener {

            val survey = Survey("satisfied",formatter.format(Calendar.getInstance().time))
            ConfirmationDialog(this,database,survey,ArrayList()).show()

        }

        verySatisfied.setOnClickListener {
            val survey = Survey("Very satisfied",formatter.format(Calendar.getInstance().time))
            ConfirmationDialog(this,database,survey,ArrayList()).show()


        }



        options.setOnClickListener {

            val anim = AnimationButton(this)
            var animationResources = R.anim.fab_show
            var color = resources.getColor(R.color.Options_background,null)
            options.setImageDrawable(resources.getDrawable(R.drawable.ic_remove, null))
            if (isOpen){
                animationResources = R.anim.fab_hide
                color = Color.TRANSPARENT
                options.setImageDrawable(resources.getDrawable(R.drawable.ic_add,null))
            }
            anim.move(export,animationResources)
            anim.move(settings,animationResources)
            fab.setBackgroundColor(color)

            isOpen=!isOpen
            fab.isClickable = isOpen
        }


        settings.setOnClickListener {
            val intent = Intent(this, MySetting::class.java).apply {
                putExtra("extra_message", "message")
            }
            startActivity(intent)

        }


        export.setOnClickListener {

            val dialog = MyProgressDialog(this)

            dialog.show()

            Thread(ExportDatabaseInCSV(database,this,dialog)).start()
        }


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
        }


    private fun requestPermission(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
           if (!Environment.isExternalStorageEmulated()){
               ActivityCompat.requestPermissions(this,
                   arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.MANAGE_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
           }

        }else{
              if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                  != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){

                  ActivityCompat.requestPermissions(this,
                      arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1)


              }
        }

    }

    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            if (window.insetsController != null) {
                window.insetsController?.hide(
                    WindowInsets.Type.statusBars()
                            or WindowInsets.Type.navigationBars())
                window.insetsController?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }

    }







    companion object{
         var isOpen :Boolean = false
         lateinit var  prf: SharedPreferences
         val listKey = arrayOf("reason_one_editText", "reason_two_editText", "reason_three_editText")

    }

}


