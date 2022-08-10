package uz.gita.noteappjt.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteappjt.R
import uz.gita.noteappjt.databinding.ScreenAddTaskBinding
import uz.gita.noteappjt.data.model.TaskData
import uz.gita.noteappjt.presentation.viewmodel.AddTaskViewModel
import uz.gita.noteappjt.presentation.viewmodel.impl.AddTaskViewModelImpl

@AndroidEntryPoint
class AddTaskScreen: Fragment(R.layout.screen_add_task) {
    private val binding by viewBinding(ScreenAddTaskBinding::bind)
    private val viewModel: AddTaskViewModel by viewModels<AddTaskViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backButton.setOnClickListener{ findNavController().popBackStack() }

    }

    override fun onPause() {
        if(binding.taskAdd.text?.isNotEmpty() == true) saveTask()
        super.onPause()
    }

    private fun saveTask() {
        binding.taskAdd.let {
            viewModel.addNewTask(TaskData(title = binding.taskAdd.text.toString(),
                createdTime = System.currentTimeMillis()))
        }
    }
}