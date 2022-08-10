package uz.gita.noteappjt.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.noteappjt.presentation.viewmodel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(): SplashViewModel, ViewModel() {
    override val openNextScreenLiveData =  MutableLiveData<Unit>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            openNextScreenLiveData.postValue(Unit)
        }
    }
}