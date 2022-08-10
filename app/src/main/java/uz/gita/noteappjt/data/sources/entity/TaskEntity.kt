package uz.gita.noteappjt.data.sources.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.noteappjt.data.model.TaskData

@Entity
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val createdTime: Long = 0,
    val selected: Int = 0,
    val isPinned: Boolean = false,
    var isTrash: Int = 0
)

fun TaskEntity.toTaskData() = TaskData(id, title, createdTime, selected, isPinned, isTrash)