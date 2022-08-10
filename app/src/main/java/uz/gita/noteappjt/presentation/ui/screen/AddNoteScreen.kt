package uz.gita.noteappjt.presentation.ui.screen

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.wordpress.aztec.Aztec
import org.wordpress.aztec.ITextFormat
import org.wordpress.aztec.toolbar.IAztecToolbarClickListener
import uz.gita.noteappjt.R
import uz.gita.noteappjt.databinding.ScreenAddNoteBinding
import uz.gita.noteappjt.data.model.NoteData
import uz.gita.noteappjt.presentation.viewmodel.AddNoteViewModel
import uz.gita.noteappjt.presentation.viewmodel.impl.AddNoteViewModelImpl

@AndroidEntryPoint
class AddNoteScreen: Fragment(R.layout.screen_add_note) {
    private val binding by viewBinding(ScreenAddNoteBinding::bind)
    private val viewModel: AddNoteViewModel by viewModels<AddNoteViewModelImpl>()
    private val tagList = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding){
        backButton.setOnClickListener{ findNavController().popBackStack() }

//        tagList.add("default")

//        saveTag.setOnClickListener {
//            val tag = tagWriter.text.toString()
//            tagWriter.text = null
//            tagList.add(tag)
//            val chip: Chip = Chip(requireContext())
//            chip.text = "#${tag}"
//            chip.isCheckable = true
//            chip.isClickable = false
//            chipGroup.addView(chip)
//
//        }

        // toolbar listeners
        Aztec.with(
            visualEditor,
            aztecTool,
            object : IAztecToolbarClickListener {
                override fun onToolbarCollapseButtonClicked() {}

                override fun onToolbarExpandButtonClicked() {}

                override fun onToolbarFormatButtonClicked(format: ITextFormat, isKeyboardShortcut: Boolean) {}

                override fun onToolbarHeadingButtonClicked() {}

                override fun onToolbarHtmlButtonClicked() {}

                override fun onToolbarListButtonClicked() {}

                override fun onToolbarMediaButtonClicked(): Boolean = false

            }
        )
        visualEditor.setTextColor(Color.BLACK)
        visualEditor.gravity = View.TEXT_ALIGNMENT_TEXT_START
    }

    override fun onPause() {
        saveNote()
        super.onPause()
    }

    private fun saveNote() {
        if(!binding.visualEditor.isEmpty()) {
            viewModel.addNewNote(NoteData(
                title = binding.titleNote.text.toString(),
                note = binding.visualEditor.toPlainHtml(),
                tag = tagList,
                createdTime = System.currentTimeMillis(),
                isPinned = false,
                isTrash = 0
            ))
        }

    }}