package uz.gita.noteappjt.presentation.viewmodel

import uz.gita.noteappjt.data.model.TaskData

interface EditTaskViewModel {
    fun updateTask(taskData: TaskData)
}