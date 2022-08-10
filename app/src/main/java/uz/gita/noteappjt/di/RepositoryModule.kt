package uz.gita.noteappjt.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.noteappjt.domain.repository.AppRepository
import uz.gita.noteappjt.domain.repository.NoteRepository
import uz.gita.noteappjt.domain.repository.TaskRepository
import uz.gita.noteappjt.domain.repository.impl.AppRepositoryImpl
import uz.gita.noteappjt.domain.repository.impl.NoteRepositoryImpl
import uz.gita.noteappjt.domain.repository.impl.TaskRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @[Binds Singleton]
    fun getAppRepository(impl: AppRepositoryImpl): AppRepository

    @[Binds Singleton]
    fun getNoteRepository(impl: NoteRepositoryImpl): NoteRepository

    @[Binds Singleton]
    fun getTaskRepository(impl: TaskRepositoryImpl): TaskRepository
}