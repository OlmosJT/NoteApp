package uz.gita.noteappjt.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteappjt.data.model.NoteData
import uz.gita.noteappjt.domain.usecase.NoteUseCase
import uz.gita.noteappjt.presentation.viewmodel.EditNoteViewModel
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModelImpl @Inject constructor(
    private val noteUseCase: NoteUseCase
): ViewModel(), EditNoteViewModel {
    override val errorLiveData = MutableLiveData<String>()
    override val successLiveData = MutableLiveData<String>()

    override fun updateNote(noteData: NoteData) {
        noteUseCase.update(noteData).onEach {
            successLiveData.value = "Updated"
            it.onSuccess {
//                loadNotes()
            }
        }.launchIn(viewModelScope)
    }
}
