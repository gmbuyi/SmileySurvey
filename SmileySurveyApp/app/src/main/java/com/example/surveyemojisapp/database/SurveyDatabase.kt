package com.decathlon.sasdec.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.decathlon.sasdec.dao.DaoReasons
import com.example.surveyemojisapp.dao.DaoSurvey
import com.example.surveyemojisapp.model.Reasons
import com.example.surveyemojisapp.model.Survey


@Database(entities = [Survey::class, Reasons ::class], version = 2)
abstract class SurveyDatabase : RoomDatabase() {

    abstract fun surveyDao(): DaoSurvey
    abstract fun raisonsDao() : DaoReasons


    companion object {
        private var instance: SurveyDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): SurveyDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, SurveyDatabase::class.java,
                    "survey_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

            return instance!!

        }
    }

}