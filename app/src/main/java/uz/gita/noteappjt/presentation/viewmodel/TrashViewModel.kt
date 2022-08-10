package uz.gita.noteappjt.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.noteappjt.data.model.NoteData
import uz.gita.noteappjt.data.sources.entity.NoteEntity

interface TrashViewModel {
    val trashesLiveData: LiveData<List<NoteEntity>>
    val errorLiveData: LiveData<String>
    val successLiveData: LiveData<String>
    val showClearDialogLiveData: LiveData<Unit>
    val emptyLiveData: LiveData<Unit>
    val nonEmptyLiveData: LiveData<Unit>

    fun loadTrash()
    fun deleteNote(noteData: NoteData)
    fun deleteOldTrash()
    fun updateNote(noteData: NoteData)
    fun showClearDialog()

    fun emptyData()
    fun nonEmptyData()
}