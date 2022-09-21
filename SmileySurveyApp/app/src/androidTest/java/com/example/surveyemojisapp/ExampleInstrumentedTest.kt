package com.example.surveyemojisapp

import android.os.Environment
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decathlon.sasdec.database.SurveyDatabase
import com.example.surveyemojisapp.model.Survey

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.io.File
import java.util.concurrent.Executors

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.surveyemojisapp", appContext.packageName)
    }

    @Test
    fun createDatabase(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val data = SurveyDatabase.getInstance(appContext)
        val dao = data.surveyDao()
        val survey = Survey("Stisfied","768")

         assertEquals(1, dao.getSurveyWithRaisons()[0].survey.surveyId)
         assertEquals("768", dao.getSurveyWithRaisons()[0].survey.datetime)
    }
    @Test
    fun createCsv(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val data = SurveyDatabase.getInstance(appContext)
        val executor = Executors.newSingleThreadExecutor()
        File(appContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "Exprt.csv").mkdir()
        executor.execute(ExportDatabaseInCSV(data, appContext))
        //assertTrue(File(appContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.path).exists())
    }
}