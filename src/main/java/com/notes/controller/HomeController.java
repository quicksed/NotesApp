package com.notes.controller;

import com.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NoteService noteService;

    @GetMapping("/")
    public String getHomePage() {
        return "redirect:home";
    }

    @GetMapping("/home")
    public String getHomePage(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("message", "Вы не авторизованы");
            return "home_unauthorized";
        } else {
            Integer notesCount = noteService.getNotesCountByUserEmail(principal.getName());

            model.addAttribute("notesCount", notesCount);
            model.addAttribute("message", String.format("Привет, %s", principal.getName()));

            return "home";
        }
    }
}
