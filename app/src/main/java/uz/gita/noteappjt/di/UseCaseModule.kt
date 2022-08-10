package uz.gita.noteappjt.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.noteappjt.domain.usecase.AddNoteUseCase
import uz.gita.noteappjt.domain.usecase.AddTaskUseCase
import uz.gita.noteappjt.domain.usecase.NoteUseCase
import uz.gita.noteappjt.domain.usecase.TaskUseCase
import uz.gita.noteappjt.domain.usecase.impl.AddNoteUseCaseImpl
import uz.gita.noteappjt.domain.usecase.impl.AddTaskUseCaseImpl
import uz.gita.noteappjt.domain.usecase.impl.NoteUseCaseImpl
import uz.gita.noteappjt.domain.usecase.impl.TaskUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun getNoteUseCase(impl : NoteUseCaseImpl) : NoteUseCase

    @Binds
    fun getAddNoteUseCase(impl : AddNoteUseCaseImpl) : AddNoteUseCase

    @Binds
    fun getTaskUseCase(impl : TaskUseCaseImpl) : TaskUseCase

    @Binds
    fun getAddTaskUseCase(impl : AddTaskUseCaseImpl) : AddTaskUseCase

}