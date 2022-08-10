package uz.gita.noteappjt.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.gita.noteappjt.data.model.TaskData
import uz.gita.noteappjt.domain.usecase.AddTaskUseCase
import uz.gita.noteappjt.presentation.viewmodel.AddTaskViewModel
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModelImpl @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase
): ViewModel(), AddTaskViewModel {
    override fun addNewTask(taskData: TaskData) {
        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase.addTask(taskData).collect()
        }
        return
    }
}