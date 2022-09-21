package com.example.surveyemojisapp.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.surveyemojisapp.model.Survey
import com.example.surveyemojisapp.model.SurveyWithRaisons


@Dao
interface DaoSurvey {
    @Insert
    fun insert(survey :Survey):Long

    @Query("delete from survey_tab")
    fun clearTable()


    @Transaction
    @Query("SELECT * FROM survey_tab")
    fun getSurveyWithRaisons(): List<SurveyWithRaisons>

}