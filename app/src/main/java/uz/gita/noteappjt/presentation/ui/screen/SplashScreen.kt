package uz.gita.noteappjt.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteappjt.R
import uz.gita.noteappjt.databinding.ScreenSplashBinding
import uz.gita.noteappjt.presentation.viewmodel.SplashViewModel
import uz.gita.noteappjt.presentation.viewmodel.impl.SplashViewModelImpl

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen: Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewmodel: SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewmodel.openNextScreenLiveData.observe(viewLifecycleOwner, openNextScreenObserver)
    }

    private val openNextScreenObserver = Observer<Unit>{
        findNavController().navigate(R.id.action_splashScreen_to_mainScreen)
    }
}