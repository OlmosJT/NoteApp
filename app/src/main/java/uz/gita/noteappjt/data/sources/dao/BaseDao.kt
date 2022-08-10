package uz.gita.noteappjt.data.sources.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T)

    @Update
    fun update(t: T)

    @Delete
    fun delete(t: T)

}