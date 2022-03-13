package com.notes.dto.mapper;

import com.notes.dto.note.NoteCreateDto;
import com.notes.dto.note.NoteDto;
import com.notes.dto.note.NoteUpdateDto;
import com.notes.entity.Note;
import com.notes.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteMapper {

    public NoteDto entityToNoteDto(Note note) {
        NoteDto noteDto = new NoteDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        noteDto.setId(note.getId());
        noteDto.setName(note.getName());
        noteDto.setCreationDate(note.getCreationDate().toLocalDateTime()
                .format(formatter));
        noteDto.setIsDeleted(note.getIsDeleted());
        noteDto.setUserId(note.getUser().getId());

        return noteDto;
    }

    public List<NoteDto> entityToNoteDto(List<Note> notes) {
        return notes.stream()
                .map(this::entityToNoteDto)
                .collect(Collectors.toList());
    }

    public Note noteCreateDtoToEntity(NoteCreateDto noteCreateDto, User user) {
        Note note = new Note();
        note.setName(noteCreateDto.getName());
        note.setCreationDate(note.getCreationDate());
        note.setIsDeleted(false);
        note.setUser(user);

        return note;
    }

    public Note noteUpdateDtoToEntity(NoteUpdateDto noteUpdateDto, User user) {
        Note note = new Note();
        note.setId(noteUpdateDto.getId());
        note.setName(noteUpdateDto.getName());
        note.setCreationDate(note.getCreationDate());
        note.setIsDeleted(noteUpdateDto.getIsDeleted());
        note.setUser(user);

        return note;
    }
}
