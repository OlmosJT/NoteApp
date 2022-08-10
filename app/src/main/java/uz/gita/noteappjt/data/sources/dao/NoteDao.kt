package uz.gita.noteappjt.data.sources.dao

import androidx.room.*
import uz.gita.noteappjt.data.sources.entity.NoteEntity

@Dao
interface NoteDao : BaseDao<NoteEntity>{

    @Query("SELECT * FROM NoteEntity WHERE isTrash = 0 ORDER BY createdTime DESC")
    fun getAllNotes() : List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE isTrash = 1 ORDER BY createdTime DESC")
    fun getAllTrash() : List<NoteEntity>

    @Query("DELETE FROM NoteEntity WHERE isTrash = 1")
    fun deleteOldTrash()
}