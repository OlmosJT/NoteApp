package uz.gita.noteappjt.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import uz.gita.noteappjt.data.model.TaskData
import uz.gita.noteappjt.domain.usecase.TaskUseCase
import uz.gita.noteappjt.presentation.viewmodel.EditTaskViewModel
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModelImpl @Inject constructor(
    private val taskUseCase: TaskUseCase
): ViewModel(), EditTaskViewModel {
    override fun updateTask(taskData: TaskData) {
        taskUseCase.updateTask(taskData).launchIn(viewModelScope)
    }

}
