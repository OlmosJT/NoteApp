package uz.gita.noteappjt.data.sources.dao

import androidx.room.Dao
import androidx.room.Query
import uz.gita.noteappjt.data.sources.entity.TaskEntity

@Dao
interface TaskDao: BaseDao<TaskEntity> {
    // get all tasks
    @Query("SELECT * FROM TaskEntity")
    fun getTasks(): List<TaskEntity>

    // add selected task
    @Query("UPDATE TaskEntity SET selected = 1 WHERE  id = :id")
    fun addCheck(id: Long)

    // delete select task
    @Query("UPDATE TaskEntity SET selected = 0 WHERE id = :id")
    fun deleteCheck(id: Long)
}