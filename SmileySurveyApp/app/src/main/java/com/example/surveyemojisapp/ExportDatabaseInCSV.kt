package com.example.surveyemojisapp

import android.app.Activity
import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.decathlon.sasdec.database.SurveyDatabase
import com.example.surveyemojisapp.mydialogs.MyProgressDialog
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.*

class ExportDatabaseInCSV(val database: SurveyDatabase, val context: Context, val alertDialog: MyProgressDialog): Runnable {

    override fun run() {

        val formatter = SimpleDateFormat("yyMMdd_hhmmss")

        val listSurvey = database.surveyDao().getSurveyWithRaisons()
        val writer = Files.newBufferedWriter(Paths.get(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.path + "/export"+formatter.format(Calendar.getInstance().time)+".csv"))
        val csvPrinter = CSVPrinter(writer, CSVFormat.DEFAULT
            .withHeader("ID_survey", "Details", "raisons", "date_and_time"))

        for(survey in listSurvey){
            if (survey.listReasons.isEmpty()){
                csvPrinter.printRecord(survey.survey.surveyId,
                                        survey.survey.details,
                                         "",
                                         survey.survey.datetime)
            } else {

                for (raisons in survey.listReasons){
                    csvPrinter.printRecord(survey.survey.surveyId,
                        survey.survey.details,
                        raisons.details,
                        survey.survey.datetime)

                }

            }


        }

        csvPrinter.flush()
        csvPrinter.close()

        val act = context as Activity

        act.runOnUiThread {
            alertDialog.cancel()
            Toast.makeText(context,"CSV file exported",Toast.LENGTH_LONG).show()
        }
    }




}