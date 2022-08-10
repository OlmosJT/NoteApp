package uz.gita.noteappjt.data.model

import uz.gita.noteappjt.data.sources.entity.TaskEntity
import java.io.Serializable

data class TaskData (
    val id: Long = 0,
    val title: String,
    val createdTime: Long = 0,
    val selected: Int = 0,
    val isPinned: Boolean = false,
    var isTrash: Int = 0
): Serializable

fun TaskData.toEntity() = TaskEntity(id=id, title = title, createdTime = createdTime, selected = selected, isPinned = isPinned, isTrash = isTrash)