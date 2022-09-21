package com.example.surveyemojisapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Raisons_tab")
data class Reasons (val survey: Int,
                    val details: String,
                    @PrimaryKey(autoGenerate = true) val id: Int? = null){
}