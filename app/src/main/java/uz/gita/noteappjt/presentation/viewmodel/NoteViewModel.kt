package uz.gita.noteappjt.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.noteappjt.data.model.NoteData
import uz.gita.noteappjt.data.sources.entity.NoteEntity

interface NoteViewModel {
    val notesLiveData: LiveData<List<NoteEntity>>
    val errorLiveData: LiveData<String>
    val successLiveData: LiveData<String>

    val emptyLiveData: LiveData<Unit>
    val nonEmptyLiveData: LiveData<Unit>

    fun loadNotes()

    fun deleteNote(noteData: NoteData)
    fun updateNote(noteData: NoteData)

    fun emptyData()
    fun nonEmptyData()

}