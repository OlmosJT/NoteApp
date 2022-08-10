package uz.gita.noteappjt.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteappjt.data.model.NoteData
import uz.gita.noteappjt.data.model.toEntity
import uz.gita.noteappjt.domain.repository.NoteRepository
import uz.gita.noteappjt.domain.usecase.AddNoteUseCase
import javax.inject.Inject

class AddNoteUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
): AddNoteUseCase {
    override fun addNote(noteData: NoteData) = flow<Result<Unit>> {
        emit(Result.success(noteRepository.addNote(noteData.toEntity())))
    }.flowOn(Dispatchers.IO)
}