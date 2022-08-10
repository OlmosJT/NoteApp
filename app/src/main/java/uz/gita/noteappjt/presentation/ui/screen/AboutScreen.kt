package uz.gita.noteappjt.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteappjt.R
import uz.gita.noteappjt.databinding.ScreenAboutBinding

@AndroidEntryPoint
class AboutScreen: Fragment(R.layout.screen_about) {
    private val binding by viewBinding(ScreenAboutBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        backButton.setOnClickListener { findNavController().popBackStack() }
    }
}