package com.example.surveyemojisapp.model

import androidx.room.Embedded
import androidx.room.Relation


data class SurveyWithRaisons (
@Embedded val survey : Survey,
    @Relation (parentColumn = "surveyId",
               entityColumn = "survey")

    val listReasons : List<Reasons>

)