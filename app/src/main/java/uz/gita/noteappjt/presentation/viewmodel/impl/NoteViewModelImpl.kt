package uz.gita.noteappjt.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteappjt.data.model.NoteData
import uz.gita.noteappjt.data.sources.entity.NoteEntity
import uz.gita.noteappjt.domain.usecase.NoteUseCase
import uz.gita.noteappjt.presentation.viewmodel.NoteViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModelImpl @Inject constructor(
    private val noteUseCase: NoteUseCase
): ViewModel(), NoteViewModel {
    override val notesLiveData =  MutableLiveData<List<NoteEntity>>()
    override val errorLiveData = MutableLiveData<String>()
    override val successLiveData = MutableLiveData<String>()
    override val emptyLiveData = MutableLiveData<Unit>()
    override val nonEmptyLiveData = MutableLiveData<Unit>()

    override fun loadNotes() {
        noteUseCase.getNotes().onEach {
            it.onSuccess { note ->
                notesLiveData.value = note
            }.onFailure {
                errorLiveData.value = "Error"
            }
        }.launchIn(viewModelScope)
    }

    override fun deleteNote(noteData: NoteData) {
        noteUseCase.delete(noteData).onEach {
            it.onSuccess {
                successLiveData.value = "Deleted"
                loadNotes()
            }
        }.launchIn(viewModelScope)
    }

    override fun updateNote(noteData: NoteData) {
        noteUseCase.update(noteData).onEach {
            it.onSuccess {
                successLiveData.value = "Updated"
                loadNotes()
            }
        }.launchIn(viewModelScope)
    }

    override fun emptyData() {
        emptyLiveData.value = Unit
    }

    override fun nonEmptyData() {
        nonEmptyLiveData.value = Unit
    }
}