package uz.gita.noteappjt.presentation.ui.screen

import android.annotation.SuppressLint
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
import uz.gita.noteappjt.databinding.ScreenEditNoteBinding
import uz.gita.noteappjt.data.model.NoteData
import uz.gita.noteappjt.data.sources.entity.NoteEntity
import uz.gita.noteappjt.data.sources.entity.toNoteData
import uz.gita.noteappjt.presentation.viewmodel.EditNoteViewModel
import uz.gita.noteappjt.presentation.viewmodel.impl.EditNoteViewModelImpl

@AndroidEntryPoint
class EditNoteScreen: Fragment(R.layout.screen_edit_note) {
    private val binding by viewBinding(ScreenEditNoteBinding::bind)
    private val viewModel: EditNoteViewModel by viewModels<EditNoteViewModelImpl>()
    private val tagList = ArrayList<String>()
    private lateinit var data : NoteData

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding){
        arguments?.let {
            data = (it.getSerializable("DATA") as NoteEntity).toNoteData()
        }
        titleNote.setText(data.title)
        visualEditor.fromHtml(data.note)
//        if(data.tag.size > 1) addTagsFromData(data.tag)

        backButton.setOnClickListener{ findNavController().popBackStack() }

//        saveTag.setOnClickListener {
//            val tag = tagWriter.text.toString()
//            tagWriter.text = null
//            if(tag.isNotEmpty()) {
//                tagList.add(tag)
//                val chip: Chip = Chip(requireContext())
//                chip.text = "#${tag}"
//                chip.isCheckable = true
//                chip.isClickable = false
//                chipGroup.addView(chip)
//            }
//        }

        //----------------------------------------
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
        //----------------------------------------
    }

    override fun onPause() {
        saveNote()
        super.onPause()
    }

    private fun saveNote() {
        if(!binding.visualEditor.isEmpty()) {
            viewModel.updateNote(
                NoteData(
                    id = data.id,
                title = binding.titleNote.text.toString(),
                note = binding.visualEditor.toPlainHtml(),
                tag = tagList,
                createdTime = System.currentTimeMillis(),
                isPinned = data.isPinned,
                isTrash = 0
            )
            )
        }

    }

//    private fun addTagsFromData(list : List<String>) {
//        for(st in list){
//            val chip: Chip = Chip(requireContext())
//            chip.text = "#${st}"
//            chip.isCheckable = true
//            chip.isClickable = false
//            binding.chipGroup.addView(chip)
//        }
//    }
}