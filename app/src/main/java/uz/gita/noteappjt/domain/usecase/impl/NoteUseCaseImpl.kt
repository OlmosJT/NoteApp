package uz.gita.noteappjt.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteappjt.data.model.NoteData
import uz.gita.noteappjt.data.model.toEntity
import uz.gita.noteappjt.data.sources.entity.NoteEntity
import uz.gita.noteappjt.domain.repository.NoteRepository
import uz.gita.noteappjt.domain.usecase.NoteUseCase
import javax.inject.Inject

class NoteUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
): NoteUseCase {
    override fun getNotes() = flow<Result<List<NoteEntity>>> {
        emit(
            Result.success(
                noteRepository.getNotes().sortedWith { p0, p1 ->
                    (p1.createdTime - p0.createdTime).toInt()
                }
            )
        )
    }.flowOn(Dispatchers.IO)

    override fun getTrash() = flow<Result<List<NoteEntity>>> {
        emit(
            Result.success(
                noteRepository.getTrash().sortedWith { p0, p1 ->
                    (p1.createdTime - p0.createdTime).toInt()
                }
            )
        )
    }.flowOn(Dispatchers.IO)

    override fun deleteOldTrash() = flow<Result<Unit>> {
        emit(Result.success(noteRepository.deleteOldTrash()))
    }.flowOn(Dispatchers.IO)

    override fun delete(noteData: NoteData) = flow<Result<Unit>> {
        emit(Result.success(noteRepository.deleteNote(noteData.toEntity())))
    }.flowOn(Dispatchers.IO)

    override fun update(noteData: NoteData) = flow<Result<Unit>> {
        emit(Result.success(noteRepository.updateNote(noteData.toEntity())))
    }.flowOn(Dispatchers.IO)

}