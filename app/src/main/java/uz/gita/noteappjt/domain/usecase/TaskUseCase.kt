package uz.gita.noteappjt.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.noteappjt.data.model.TaskData
import uz.gita.noteappjt.data.sources.entity.TaskEntity

interface TaskUseCase {
    fun getTasks(): Flow<Result<List<TaskEntity>>>
    fun addCheck(id: Long): Flow<Result<Unit>>
    fun deleteCheck(id: Long): Flow<Result<Unit>>
    fun deleteTask(taskData: TaskData):Flow<Result<Unit>>
    fun updateTask(taskData: TaskData): Flow<Unit>
}