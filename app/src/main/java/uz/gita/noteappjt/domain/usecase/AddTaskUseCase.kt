package uz.gita.noteappjt.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.noteappjt.data.model.TaskData

interface AddTaskUseCase {
    fun addTask(taskData: TaskData): Flow<Result<Unit>>
}