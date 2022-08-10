package uz.gita.noteappjt.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.noteappjt.data.model.NoteData

interface EditNoteViewModel {
    val errorLiveData: LiveData<String>
    val successLiveData: LiveData<String>

    fun updateNote(noteData: NoteData)
}