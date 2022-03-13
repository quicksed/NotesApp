package com.notes.controller;

import com.notes.dto.note.NoteCreateDto;
import com.notes.dto.note.NoteDto;
import com.notes.exception.EmptyNoteException;
import com.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/notes")
    public String getUserNotes(Model model, Principal principal) {
        List<NoteDto> notes = noteService.getNotesByUserEmail(principal.getName());
        model.addAttribute("notes", notes);

        return "notes";
    }

    @GetMapping("/notes/add")
    public String showAddNoteForm(Model model, Principal principal) {
        return "add_note";
    }

    @PostMapping(path = "/notes/add", consumes = "application/x-www-form-urlencoded")
    public String createNote(NoteCreateDto noteCreateDto, Model model, Principal principal) {
        if (noteCreateDto.getName().isEmpty()) {
            throw new EmptyNoteException();
        }

        noteService.createNote(noteCreateDto, principal);

        return "redirect:";
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable("id") Long id, Principal principal) {
        noteService.deleteNote(id, principal);
    }
}
