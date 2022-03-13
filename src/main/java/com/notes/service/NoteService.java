package com.notes.service;

import com.notes.dto.note.NoteCreateDto;
import com.notes.dto.note.NoteDto;
import com.notes.dto.note.NoteUpdateDto;

import java.security.Principal;
import java.util.List;

public interface NoteService {

    NoteDto getNoteById(Long id);

    Integer getNotesCountByUserEmail(String email);

    List<NoteDto> getNotesByUserId(Long userId);

    List<NoteDto> getNotesByUserEmail(String email);

    NoteDto createNote(NoteCreateDto noteCreateDto, Principal principal);

    NoteDto updateNote(NoteUpdateDto noteUpdateDto, Principal principal);

    void deleteNote(Long noteId, Principal principal);
}
