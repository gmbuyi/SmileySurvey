package com.decathlon.sasdec.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.surveyemojisapp.model.Reasons


@Dao
interface DaoReasons {

    @Insert
    fun insert(reasons : Reasons)

    @Query("delete from raisons_tab")
    fun clearTable()
}