package uz.gita.noteappjt.domain.repository.impl

import uz.gita.noteappjt.data.sources.dao.TaskDao
import uz.gita.noteappjt.data.sources.entity.TaskEntity
import uz.gita.noteappjt.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
): TaskRepository {
    override suspend fun addTasks(taskEntity: TaskEntity) {
        taskDao.insert(taskEntity)
    }

    override suspend fun deleteTask(taskEntity: TaskEntity) {
        taskDao.delete(taskEntity)
    }

    override suspend fun update(taskEntity: TaskEntity) {
        taskDao.update(taskEntity)
    }

    override suspend fun checkAdd(id: Long) {
        taskDao.addCheck(id)
    }

    override suspend fun checkDelete(id: Long) {
        taskDao.deleteCheck(id)
    }

    override suspend fun getTasks(): List<TaskEntity> = taskDao.getTasks()
}