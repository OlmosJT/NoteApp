package uz.gita.noteappjt.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.noteappjt.data.model.NoteData

interface AddNoteUseCase {
    fun addNote(noteData: NoteData): Flow<Result<Unit>>
}