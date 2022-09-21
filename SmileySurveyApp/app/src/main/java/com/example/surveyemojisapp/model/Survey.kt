package com.example.surveyemojisapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Survey_tab")
data class Survey(
    var details: String,
    val datetime: String,
    @PrimaryKey(autoGenerate = true)
                  val surveyId: Int? = null) {


}