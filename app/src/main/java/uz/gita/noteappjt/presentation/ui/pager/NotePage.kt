package uz.gita.noteappjt.presentation.ui.pager

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteappjt.R
import uz.gita.noteappjt.databinding.PageNoteBinding
import uz.gita.noteappjt.data.model.NoteData
import uz.gita.noteappjt.data.sources.entity.NoteEntity
import uz.gita.noteappjt.data.sources.entity.toNoteData
import uz.gita.noteappjt.presentation.ui.adapter.ListNotesAdapter
import uz.gita.noteappjt.presentation.viewmodel.NoteViewModel
import uz.gita.noteappjt.presentation.viewmodel.impl.NoteViewModelImpl

@AndroidEntryPoint
class NotePage : Fragment(R.layout.page_note) {
    private val binding by viewBinding(PageNoteBinding::bind)
    private val viewmodel: NoteViewModel by viewModels<NoteViewModelImpl>()
    private val listNotesAdapter: ListNotesAdapter = ListNotesAdapter()
    private var updateNoteData: ((NoteData) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.listNotes.adapter = listNotesAdapter
        binding.listNotes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        listNotesAdapter.setOnItemLongClickListener {
            showBottomSheetDialog(it)
//            it.isTrash = 1
//            viewmodel.updateNote(it.toNoteData())
        }

        viewmodel.notesLiveData.observe(viewLifecycleOwner, notesObserver)
        viewmodel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewmodel.emptyLiveData.observe(viewLifecycleOwner, emptyObserver)
        viewmodel.nonEmptyLiveData.observe(viewLifecycleOwner, nonEmptyObserver)
        viewmodel.loadNotes()
    }

    private val notesObserver = Observer<List<NoteEntity>> {
        if(it.isEmpty()) viewmodel.emptyData()
        else viewmodel.nonEmptyData()
        listNotesAdapter.submitList(it)
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), "Error occured!", Toast.LENGTH_SHORT).show()
    }

    private val emptyObserver = Observer<Unit> {
        binding.placeHolder.visibility = View.VISIBLE
    }

    private val nonEmptyObserver = Observer<Unit> {
        binding.placeHolder.visibility = View.GONE
    }

    fun setUpdateNoteData(block: (NoteData) -> Unit) {
        updateNoteData = block
    }

    private fun showBottomSheetDialog(data: NoteEntity) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout)

        bottomSheetDialog.findViewById<TextView>(R.id.btnDelete)?.setOnClickListener {
            data.isTrash = 1
            viewmodel.updateNote(data.toNoteData())
            bottomSheetDialog.cancel()
        }

        bottomSheetDialog.findViewById<TextView>(R.id.btnEdit)?.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("DATA", data)
            findNavController().navigate(R.id.action_mainScreen_to_editNoteScreen, bundle)
            bottomSheetDialog.cancel()
        }
        bottomSheetDialog.show()
    }
}