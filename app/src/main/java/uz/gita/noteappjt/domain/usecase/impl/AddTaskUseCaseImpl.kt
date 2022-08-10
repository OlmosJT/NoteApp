package uz.gita.noteappjt.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteappjt.data.model.TaskData
import uz.gita.noteappjt.data.model.toEntity
import uz.gita.noteappjt.domain.repository.TaskRepository
import uz.gita.noteappjt.domain.usecase.AddTaskUseCase
import javax.inject.Inject

class AddTaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
): AddTaskUseCase {
    override fun addTask(taskData: TaskData) =  flow<Result<Unit>> {
        emit(Result.success(taskRepository.addTasks(taskData.toEntity())))
    }.flowOn(Dispatchers.IO)
}