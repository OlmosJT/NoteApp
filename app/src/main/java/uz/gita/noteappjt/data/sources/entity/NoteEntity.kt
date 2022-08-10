package uz.gita.noteappjt.data.sources.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.noteappjt.data.model.NoteData
import java.io.Serializable

@Entity
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val note: String,
    val tag: String,
    val createdTime: Long = 0,
    val isPinned: Boolean,
    var isTrash: Int = 0
) : Serializable

fun NoteEntity.toNoteData(): NoteData {
    val listTag = ArrayList<String>()
    tag.split('#')

    return NoteData(
        id = id, title = title, note = note, tag = listTag,
        createdTime = createdTime, isPinned = isPinned, isTrash = isTrash
    )
}