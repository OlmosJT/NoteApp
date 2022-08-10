package uz.gita.noteappjt.data.sources

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.noteappjt.data.sources.dao.NoteDao
import uz.gita.noteappjt.data.sources.dao.TaskDao
import uz.gita.noteappjt.data.sources.entity.NoteEntity
import uz.gita.noteappjt.data.sources.entity.TaskEntity

@Database(entities = [NoteEntity::class, TaskEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    abstract fun getTaskDao(): TaskDao

}