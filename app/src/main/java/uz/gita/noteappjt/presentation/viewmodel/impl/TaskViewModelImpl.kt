package uz.gita.noteappjt.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteappjt.data.model.TaskData
import uz.gita.noteappjt.data.sources.entity.TaskEntity
import uz.gita.noteappjt.domain.usecase.TaskUseCase
import uz.gita.noteappjt.presentation.viewmodel.TaskViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModelImpl @Inject constructor(
    private val taskUseCase: TaskUseCase
): ViewModel(), TaskViewModel {
    override val tasksLiveData = MutableLiveData<List<TaskEntity>>()
    override val errorLiveData = MutableLiveData<String>()
    override val successLiveData = MutableLiveData<String>()
    override val emptyLiveData = MutableLiveData<Unit>()
    override val nonEmptyLiveData = MutableLiveData<Unit>()

    override fun loadTask() {
        taskUseCase.getTasks().onEach {
            it.onSuccess { task ->
                tasksLiveData.value = task
            }.onFailure {
                errorLiveData.value = "Error"
            }
        }.launchIn(viewModelScope)
    }

    override fun addChecked(id: Long) {
        taskUseCase.addCheck(id).onEach {
            it.onSuccess {
                loadTask()
            }
        }.launchIn(viewModelScope)
    }

    override fun deleteChecked(id: Long) {
        taskUseCase.deleteCheck(id).onEach {
            it.onSuccess {
                loadTask()
            }
        }.launchIn(viewModelScope)
    }

    override fun deleteTask(taskData: TaskData) {
        taskUseCase.deleteTask(taskData).onEach {
            it.onSuccess {
                successLiveData.value = "Delete"
                loadTask()
            }
        }.launchIn(viewModelScope)
    }

    override fun emptyData() {
        emptyLiveData.value = Unit
    }

    override fun nonEmptyData() {
        nonEmptyLiveData.value = Unit
    }
}