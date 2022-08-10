package uz.gita.noteappjt.presentation.ui.screen

import android.annotation.SuppressLint
import android.app.AlertDialog
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
import uz.gita.noteappjt.databinding.ScreenTrashBinding
import uz.gita.noteappjt.data.sources.entity.NoteEntity
import uz.gita.noteappjt.data.sources.entity.toNoteData
import uz.gita.noteappjt.presentation.ui.adapter.ListNotesAdapter
import uz.gita.noteappjt.presentation.viewmodel.TrashViewModel
import uz.gita.noteappjt.presentation.viewmodel.impl.TrashViewModelImpl

@AndroidEntryPoint
class TrashScreen: Fragment(R.layout.screen_trash) {
    private val binding by viewBinding(ScreenTrashBinding::bind)
    private val listTrashNoteAdapter: ListNotesAdapter = ListNotesAdapter()
    private val viewmodel: TrashViewModel by viewModels<TrashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.listTrashNotes.adapter = listTrashNoteAdapter
        binding.listTrashNotes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        listTrashNoteAdapter.setOnItemLongClickListener {
            // delete or recycle
            showBottomSheetDialog(it)
        }

        binding.btnClearAllTrash.setOnClickListener {
            // dialog show and clear
            viewmodel.showClearDialog()
        }
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
        viewmodel.trashesLiveData.observe(viewLifecycleOwner, trashesObserver)
        viewmodel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewmodel.showClearDialogLiveData.observe(viewLifecycleOwner, showClearDialogObserver)
        viewmodel.emptyLiveData.observe(viewLifecycleOwner, emptyObserver)
        viewmodel.nonEmptyLiveData.observe(viewLifecycleOwner, nonEmptyObserver)
        viewmodel.loadTrash()
    }

    private fun showBottomSheetDialog(data: NoteEntity) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_trash_dialog_layout)

        bottomSheetDialog.findViewById<TextView>(R.id.btnRestore)?.setOnClickListener {
            data.isTrash = 0
            viewmodel.updateNote(data.toNoteData())
            bottomSheetDialog.cancel()
        }

        bottomSheetDialog.findViewById<TextView>(R.id.btnDelete)?.setOnClickListener {
            viewmodel.deleteNote(data.toNoteData())
            bottomSheetDialog.cancel()
        }
        bottomSheetDialog.show()
    }

    private val trashesObserver = Observer<List<NoteEntity>> {
        if(it.isEmpty()) viewmodel.emptyData()
        else viewmodel.nonEmptyData()
        listTrashNoteAdapter.submitList(it)
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

    @SuppressLint("InflateParams")
    private val showClearDialogObserver = Observer<Unit> {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_clear_trash, null)

        val dialog = AlertDialog.Builder(requireContext()).setView(view).show()

        view.findViewById<TextView>(R.id.btnAccept).setOnClickListener { viewmodel.deleteOldTrash(); dialog.dismiss() }
        view.findViewById<TextView>(R.id.btnDecline).setOnClickListener {  dialog.dismiss() }

    }
}