package pl.flaaaxxx.springnotehomeworkweek8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.flaaaxxx.springnotehomeworkweek8.model.Note;
import pl.flaaaxxx.springnotehomeworkweek8.repository.NoteRepo;

import java.util.Optional;
import java.util.stream.Stream;

@Controller
public class NoteController {

    private NoteRepo noteRepo;

    @Autowired
    public NoteController(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    @GetMapping("/show-all-notes")
    public String showNotes(Model model) {
        model.addAttribute("notes", noteRepo.findAll());
        return "show-all-notes";
        // http://localhost:8080/show-all-notes
    }

    @GetMapping("/show-note/{id}")
    public String showNote(Model model, @PathVariable(value = "id") Long id) {
        Optional<Note> note = noteRepo.findById(id);

        if(note.isPresent())
            model.addAttribute("note", note.get());
        return "show-note";
        // http://localhost:8080/show-note
    }

    @GetMapping("/add-note")
    public String addNote(Model model) {
        model.addAttribute("note", new Note());
        return "add-note";
    }

    @PostMapping("add-note-execute")
    public String addNoteExecute(@ModelAttribute Note note) {
        noteRepo.save(note);
        return "redirect:/show-all-notes";
    }

    @GetMapping("/edit-note/{id}")
    public String editNote(Model model, @PathVariable(value = "id") Long id) {
        Optional<Note> note = noteRepo.findById(id);

        if (note.isPresent())
            model.addAttribute("note", note);
        else
            model.addAttribute("note", new Note());

        return "add-note";
        // http://localhost:8080/add-note
    }

    @PostMapping("/delete-note-execute/{id}")
    public String deletenoteExecute(@PathVariable(value = "id", required = true) Long id) {
        Optional<Note> note = noteRepo.findById(id);

        if (note.isPresent())
            noteRepo.deleteById(note.get().getId());

        return "redirect:/show-all-notes";
    }
}
