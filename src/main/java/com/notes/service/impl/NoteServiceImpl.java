package com.notes.service.impl;

import com.notes.dto.mapper.NoteMapper;
import com.notes.dto.note.NoteCreateDto;
import com.notes.dto.note.NoteDto;
import com.notes.dto.note.NoteUpdateDto;
import com.notes.entity.Note;
import com.notes.entity.User;
import com.notes.exception.NotFoundException;
import com.notes.repository.NoteRepository;
import com.notes.repository.UserRepository;
import com.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteMapper noteMapper;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Override
    public NoteDto getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Note with id %d not found", id)));

        return noteMapper.entityToNoteDto(note);
    }

    @Override
    public Integer getNotesCountByUserEmail(String email) {
        return noteRepository.findNotesCountByUserEmail(email)
                .orElse(0);
    }

    @Override
    public List<NoteDto> getNotesByUserId(Long userId) {
        List<Note> notes = noteRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Not found notes by user with id %d", userId)));

        return noteMapper.entityToNoteDto(notes);
    }

    @Override
    public List<NoteDto> getNotesByUserEmail(String email) {
        List<Note> notes = noteRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("Not found notes by user with email %s", email)));

        return noteMapper.entityToNoteDto(notes);
    }

    @Override
    public NoteDto createNote(NoteCreateDto noteCreateDto, Principal principal) {
        User user = findUserByPrincipal(principal);
        Note note = noteMapper.noteCreateDtoToEntity(noteCreateDto, user);

        noteRepository.save(note);
        return noteMapper.entityToNoteDto(note);
    }

    @Override
    public NoteDto updateNote(NoteUpdateDto noteUpdateDto, Principal principal) {
        User user = findUserByPrincipal(principal);
        Note note = noteMapper.noteUpdateDtoToEntity(noteUpdateDto, user);

        if (!user.getId().equals(note.getUser().getId())) {
            throw new AccessDeniedException("Access denied because user doesn't own this note");
        }

        noteRepository.save(note);
        return noteMapper.entityToNoteDto(note);
    }

    @Override
    public void deleteNote(Long noteId, Principal principal) {
        User user = findUserByPrincipal(principal);
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new NotFoundException(String.format("Note with id %d not found", noteId)));

        if (!user.getId().equals(note.getUser().getId())) {
            throw new AccessDeniedException("Access denied because user doesn't own this note");
        }

        noteRepository.delete(note);
    }

    private User findUserByPrincipal(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException(String.format("Not found notes by user with email %s", principal.getName())));
    }
}
