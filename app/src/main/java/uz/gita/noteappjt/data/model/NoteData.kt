package uz.gita.noteappjt.data.model

import uz.gita.noteappjt.data.sources.entity.NoteEntity
import java.io.Serializable


data class NoteData(
    val id: Long = 0,
    val title: String,
    val note: String,
    val tag: List<String>,
    val createdTime: Long,
    val isPinned: Boolean,
    var isTrash: Int
): Serializable

fun NoteData.toEntity(): NoteEntity {
    var stringTag: String = ""
    for(t in tag){
        stringTag += "#$t"
    }
    return NoteEntity(id = id, title = title, note = note, tag = stringTag,
        createdTime = createdTime, isPinned = isPinned, isTrash = isTrash)
}