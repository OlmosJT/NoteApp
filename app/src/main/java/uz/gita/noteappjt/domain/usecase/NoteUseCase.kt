package uz.gita.noteappjt.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.noteappjt.data.model.NoteData
import uz.gita.noteappjt.data.sources.entity.NoteEntity

interface NoteUseCase {
    fun getNotes(): Flow<Result<List<NoteEntity>>>
    fun delete(noteData: NoteData): Flow<Result<Unit>>
    fun update(noteData: NoteData): Flow<Result<Unit>>

    fun getTrash(): Flow<Result<List<NoteEntity>>>
    fun deleteOldTrash(): Flow<Result<Unit>>
}