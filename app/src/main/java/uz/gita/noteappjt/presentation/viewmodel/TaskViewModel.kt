package uz.gita.noteappjt.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.noteappjt.data.model.TaskData
import uz.gita.noteappjt.data.sources.entity.TaskEntity

interface TaskViewModel {
    val tasksLiveData: LiveData<List<TaskEntity>>
    val errorLiveData: LiveData<String>
    val successLiveData: LiveData<String>

    val emptyLiveData: LiveData<Unit>
    val nonEmptyLiveData: LiveData<Unit>

    fun loadTask()
    fun addChecked(id: Long)
    fun deleteChecked(id: Long)
    fun deleteTask(taskData: TaskData)

    fun emptyData()
    fun nonEmptyData()
}