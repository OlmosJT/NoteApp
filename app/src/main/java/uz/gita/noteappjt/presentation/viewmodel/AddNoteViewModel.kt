package uz.gita.noteappjt.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.noteappjt.data.model.NoteData

interface AddNoteViewModel {
    val errorTitleLiveData: LiveData<Int>
    val errorNoteLiveData: LiveData<Int>
    val loadingLiveData: LiveData<Boolean>
    val successLiveData: LiveData<Unit>
    val backLiveData: LiveData<Unit>

    // add new note
    fun addNewNote(noteData: NoteData)
    fun back()
}