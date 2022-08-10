package uz.gita.noteappjt.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteappjt.R
import uz.gita.noteappjt.databinding.ScreenEditTaskBinding
import uz.gita.noteappjt.data.model.TaskData
import uz.gita.noteappjt.presentation.viewmodel.EditTaskViewModel
import uz.gita.noteappjt.presentation.viewmodel.impl.EditTaskViewModelImpl

@AndroidEntryPoint
class EditTaskScreen: Fragment(R.layout.screen_edit_task) {
    private val binding by viewBinding(ScreenEditTaskBinding::bind)
    private val viewModel: EditTaskViewModel by viewModels<EditTaskViewModelImpl>()
    private lateinit var data : TaskData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            data = it.getSerializable("TASK") as TaskData
        }
        binding.taskEdit.setText(data.title)

        binding.backButton.setOnClickListener{ findNavController().popBackStack() }
    }

    override fun onPause() {
        if(binding.taskEdit.text?.isNotEmpty() == true) saveTask()
        super.onPause()
    }

    private fun saveTask() {
        binding.taskEdit.let {
            viewModel.updateTask(TaskData(id = data.id, title = binding.taskEdit.text.toString(),
                createdTime = System.currentTimeMillis()))
        }
    }
}