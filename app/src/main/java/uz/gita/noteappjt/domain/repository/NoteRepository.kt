package uz.gita.noteappjt.domain.repository

import uz.gita.noteappjt.data.sources.entity.NoteEntity

interface NoteRepository {
    // insert note
    suspend fun addNote(noteEntity: NoteEntity)

    // delete note
    suspend fun deleteNote(noteEntity: NoteEntity)

    // update note
    suspend fun updateNote(noteEntity: NoteEntity)

    // getAllNotes
    suspend fun getNotes(): List<NoteEntity>

    // getAllTrash
    suspend fun getTrash(): List<NoteEntity>

    // delete old trash
    suspend fun deleteOldTrash()
}