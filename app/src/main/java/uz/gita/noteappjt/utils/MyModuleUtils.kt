package uz.gita.noteappjt.utils

import uz.gita.noteappjt.data.model.NoteData
import uz.gita.noteappjt.data.sources.entity.NoteEntity

fun NoteEntity.getNoteData(): NoteData {
    return NoteData(
        this.id,
        this.title,
        this.note,
        this.tag.split("#"),
        this.createdTime,
        this.isPinned,
        this.isTrash
    )
}