package uz.gita.noteappjt.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.noteappjt.data.sources.AppDatabase
import uz.gita.noteappjt.data.sources.dao.NoteDao
import uz.gita.noteappjt.data.sources.dao.TaskDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @[Provides Singleton]
    fun getAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "MyNote").build()

    @[Provides Singleton]
    fun getNoteDao(database: AppDatabase): NoteDao = database.getNoteDao()

    @[Provides Singleton]
    fun getTaskDao(database: AppDatabase): TaskDao = database.getTaskDao()
}