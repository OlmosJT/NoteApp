package uz.gita.noteappjt.presentation.ui.pager

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteappjt.presentation.ui.adapter.TaskAdapter
import uz.gita.noteappjt.R
import uz.gita.noteappjt.data.sources.entity.TaskEntity
import uz.gita.noteappjt.data.sources.entity.toTaskData
import uz.gita.noteappjt.databinding.PageTaskBinding
import uz.gita.noteappjt.presentation.viewmodel.TaskViewModel
import uz.gita.noteappjt.presentation.viewmodel.impl.TaskViewModelImpl

@AndroidEntryPoint
class TaskPage: Fragment(R.layout.page_task) {
    private val binding by viewBinding(PageTaskBinding::bind)
    private val viewmodel: TaskViewModel by viewModels<TaskViewModelImpl>()
    private val taskAdapter : TaskAdapter = TaskAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        listTasks.adapter = taskAdapter
        listTasks.layoutManager = LinearLayoutManager(requireContext())

        taskAdapter.setOnItemClickListener {
            if(it.selected == 0) {
                viewmodel.addChecked(it.id)
            } else {
                viewmodel.deleteChecked(it.id)
            }
            viewmodel.loadTask()
        }

        taskAdapter.setOnItemLongClickListener {
            showBottomSheetDialog(it)
        }

        viewmodel.tasksLiveData.observe(viewLifecycleOwner, tasksObserver)
        viewmodel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewmodel.emptyLiveData.observe(viewLifecycleOwner, emptyObserver)
        viewmodel.nonEmptyLiveData.observe(viewLifecycleOwner, nonEmptyObserver)
        viewmodel.loadTask()
    }

    private val tasksObserver = Observer<List<TaskEntity>> {
        if(it.isEmpty()) viewmodel.emptyData()
        else viewmodel.nonEmptyData()
        val list = ArrayList<TaskEntity>()
        list.addAll(it)
        taskAdapter.submitList(list)
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), "Error list", Toast.LENGTH_SHORT).show()
    }

    private val emptyObserver = Observer<Unit> {
        binding.placeHolder.visibility = View.VISIBLE
    }

    private val nonEmptyObserver = Observer<Unit> {
        binding.placeHolder.visibility = View.GONE
    }

    private fun showBottomSheetDialog(data: TaskEntity) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout)

        bottomSheetDialog.findViewById<TextView>(R.id.btnDelete)?.setOnClickListener {
            viewmodel.deleteTask(data.toTaskData())
            bottomSheetDialog.cancel()
        }

        bottomSheetDialog.findViewById<TextView>(R.id.btnEdit)?.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("TASK", data.toTaskData())
            findNavController().navigate(R.id.action_mainScreen_to_editTaskScreen, bundle)
            bottomSheetDialog.cancel()
        }
        bottomSheetDialog.show()
    }
}