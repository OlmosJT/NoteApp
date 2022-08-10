package uz.gita.noteappjt.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.noteappjt.presentation.viewmodel.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(): ViewModel(), MainViewModel {
    override val openAddNoteScreenLiveData =  MutableLiveData<Unit>()
    override val openAddTaskScreenLiveData =  MutableLiveData<Unit>()
    override val openDrawerLiveData = MutableLiveData<Unit>()
    override val closeDrawerLiveData = MutableLiveData<Unit>()
    override val openTrashScreenLiveData =  MutableLiveData<Unit>()
    override val openAboutScreenLiveData = MutableLiveData<Unit>()

    override fun openNextScreen(pos: Int) {
        if(pos == 0) openAddNoteScreenLiveData.value = Unit
        else openAddTaskScreenLiveData.value = Unit
    }

    override fun openTrashScreen() {
        openTrashScreenLiveData.value = Unit
    }

    override fun openAboutScreen() {
        openAboutScreenLiveData.value = Unit
    }

    override fun openDrawer() {
        openDrawerLiveData.value = Unit
    }

    override fun closeDrawer() {
        closeDrawerLiveData.value = Unit
    }
}