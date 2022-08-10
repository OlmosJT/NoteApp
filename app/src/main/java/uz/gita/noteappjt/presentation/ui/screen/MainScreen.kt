package uz.gita.noteappjt.presentation.ui.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.azeesoft.lib.colorpicker.BuildConfig
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteappjt.R
import uz.gita.noteappjt.databinding.ScreenMainNavBinding
import uz.gita.noteappjt.presentation.ui.adapter.MainViewPagerAdapter
import uz.gita.noteappjt.presentation.viewmodel.MainViewModel
import uz.gita.noteappjt.presentation.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main_nav) {
    private val binding by viewBinding(ScreenMainNavBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        binding.inner.viewPager.adapter = MainViewPagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.inner.tabLayout, binding.inner.viewPager) { tab, pos ->
            if (pos == 0) tab.text = "Notes" else tab.text = "Tasks"
        }.attach()

        //---------------------------------------- category clicks
        binding.categoryShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            var body = "Notebook is a simple notes, easy and fast notepad to edit and manage notes\n\n"
            body +="https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            val subject = "Your subject"
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, body)
            startActivity(Intent.createChooser(intent, "share using"))
        }

        binding.categoryRate.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=${requireActivity().packageName}")
                )
            )
        }

        binding.categoryAbout.setOnClickListener {
            viewModel.openAboutScreen()
        }

        //----------------------------------------

        binding.inner.addButton.setOnClickListener { viewModel.openNextScreen(binding.inner.viewPager.currentItem) }
        binding.inner.slideMenu.setOnClickListener { viewModel.openDrawer() }
        binding.categoryTrash.setOnClickListener { viewModel.openTrashScreen() }
        viewModel.openAddNoteScreenLiveData.observe(this@MainScreen, openAddNoteScreenObserver)
        viewModel.openAddTaskScreenLiveData.observe(this@MainScreen, openAddTaskScreenObserver)
        viewModel.closeDrawerLiveData.observe(this@MainScreen, closeDrawerObserver)
        viewModel.openDrawerLiveData.observe(this@MainScreen, openDrawerObserver)
        viewModel.openTrashScreenLiveData.observe(this@MainScreen, openTrashScreenObserver)
        viewModel.openAboutScreenLiveData.observe(this@MainScreen, openAboutScreenObserver)
        viewModel.closeDrawer()
    }

    private val openAddNoteScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_mainScreen_to_addNoteScreen)
    }
    private val openAddTaskScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_mainScreen_to_addTaskScreen)
    }

    private val openAboutScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_mainScreen_to_aboutScreen)
    }

    private val closeDrawerObserver = Observer<Unit> {
        binding.drawer.closeDrawer(GravityCompat.START)
    }

    private val openDrawerObserver = Observer<Unit> {
        binding.drawer.openDrawer(GravityCompat.START)
    }

    private val openTrashScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_mainScreen_to_trashScreen)
    }
}