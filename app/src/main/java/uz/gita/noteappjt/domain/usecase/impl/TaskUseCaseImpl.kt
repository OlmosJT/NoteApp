package uz.gita.noteappjt.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteappjt.data.model.TaskData
import uz.gita.noteappjt.data.model.toEntity
import uz.gita.noteappjt.data.sources.entity.TaskEntity
import uz.gita.noteappjt.domain.repository.TaskRepository
import uz.gita.noteappjt.domain.usecase.TaskUseCase
import javax.inject.Inject

class TaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
): TaskUseCase {
    override fun getTasks() = flow<Result<List<TaskEntity>>> {
        emit(
            Result.success(
                taskRepository.getTasks().sortedWith { p0, p1 -> (p1.createdTime - p0.createdTime).toInt() })
        )
    }.flowOn(Dispatchers.IO)

    override fun addCheck(id: Long) = flow<Result<Unit>> {
        emit(Result.success(taskRepository.checkAdd(id)))
    }.flowOn(Dispatchers.IO)

    override fun deleteCheck(id: Long) = flow<Result<Unit>> {
        emit(Result.success(taskRepository.checkDelete(id)))
    }.flowOn(Dispatchers.IO)

    override fun deleteTask(taskData: TaskData) = flow<Result<Unit>> {
        emit(Result.success(taskRepository.deleteTask(taskData.toEntity())))
    }.flowOn(Dispatchers.IO)

    override fun updateTask(taskData: TaskData) =  flow {
        emit(taskRepository.update(taskData.toEntity()))
    }.flowOn(Dispatchers.IO)
}