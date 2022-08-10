package uz.gita.noteappjt.presentation.viewmodel

import androidx.lifecycle.LiveData

interface MainViewModel {
    val openAddNoteScreenLiveData: LiveData<Unit>
    val openAddTaskScreenLiveData: LiveData<Unit>
    val openTrashScreenLiveData: LiveData<Unit>
    val openAboutScreenLiveData: LiveData<Unit>

    fun openNextScreen(pos: Int)
    fun openTrashScreen()
    fun openAboutScreen()
    val openDrawerLiveData: LiveData<Unit>
    val closeDrawerLiveData: LiveData<Unit>

    fun openDrawer()
    fun closeDrawer()
}