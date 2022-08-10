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
import uz.gita.noteappjt.presentation.viewmodel.TrashViewModel
import javax.inject.Inject

@HiltViewModel
class TrashViewModelImpl @Inject constructor(
    private val noteUseCase: NoteUseCase
): ViewModel(), TrashViewModel {
    override val trashesLiveData =  MutableLiveData<List<NoteEntity>>()
    override val errorLiveData =  MutableLiveData<String>()
    override val successLiveData =  MutableLiveData<String>()
    override val showClearDialogLiveData = MutableLiveData<Unit>()
    override val emptyLiveData = MutableLiveData<Unit>()
    override val nonEmptyLiveData = MutableLiveData<Unit>()

    override fun loadTrash() {
        noteUseCase.getTrash().onEach {
            it.onSuccess { note ->
                trashesLiveData.value = note
            }.onFailure {
                errorLiveData.value = "Error"
            }
        }.launchIn(viewModelScope)
    }

    override fun deleteNote(noteData: NoteData) {
        noteUseCase.delete(noteData).onEach {
            it.onSuccess {
                successLiveData.value = "Deleted"
                loadTrash()
            }
        }.launchIn(viewModelScope)
    }

    override fun deleteOldTrash() {
        noteUseCase.deleteOldTrash().onEach {
            it.onSuccess {
                successLiveData.value = "Deleted"
                loadTrash()
            }
        }.launchIn(viewModelScope)
    }

    override fun updateNote(noteData: NoteData) {
        noteUseCase.update(noteData).onEach {
            it.onSuccess {
                successLiveData.value = "Updated"
                loadTrash()
            }
        }.launchIn(viewModelScope)
    }

    override fun showClearDialog() {
        showClearDialogLiveData.value = Unit
    }

    override fun emptyData() {
        emptyLiveData.value = Unit
    }

    override fun nonEmptyData() {
        nonEmptyLiveData.value = Unit
    }
}