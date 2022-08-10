package uz.gita.noteappjt.domain.repository.impl

import uz.gita.noteappjt.data.sources.dao.NoteDao
import uz.gita.noteappjt.data.sources.entity.NoteEntity
import uz.gita.noteappjt.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
): NoteRepository {
    override suspend fun addNote(noteEntity: NoteEntity) {
        noteDao.insert(noteEntity)
    }

    override suspend fun deleteNote(noteEntity: NoteEntity) {
        noteDao.delete(noteEntity)
    }

    override suspend fun updateNote(noteEntity: NoteEntity) {
        noteDao.update(noteEntity)
    }

    override suspend fun getNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

    override suspend fun getTrash(): List<NoteEntity> {
        return noteDao.getAllTrash()
    }

    override suspend fun deleteOldTrash() {
        return noteDao.deleteOldTrash()
    }
}