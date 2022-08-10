package uz.gita.noteappjt.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import uz.gita.noteappjt.data.model.NoteData
import uz.gita.noteappjt.domain.usecase.AddNoteUseCase
import uz.gita.noteappjt.presentation.viewmodel.AddNoteViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModelImpl @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
): ViewModel(), AddNoteViewModel {
    override val errorTitleLiveData = MutableLiveData<Int>()
    override val errorNoteLiveData = MutableLiveData<Int>()
    override val loadingLiveData = MutableLiveData<Boolean>()
    override val successLiveData = MutableLiveData<Unit>()
    override val backLiveData = MutableLiveData<Unit>()

    override fun addNewNote(noteData: NoteData) {
        addNoteUseCase.addNote(noteData).flowOn(Dispatchers.IO).launchIn(viewModelScope)
        loadingLiveData.value = true
        return
    }

    override fun back() {
        backLiveData.value = Unit
    }
}