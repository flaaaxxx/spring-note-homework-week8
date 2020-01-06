package pl.flaaaxxx.springnotehomeworkweek8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.flaaaxxx.springnotehomeworkweek8.model.Note;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {
    List<Note> findAll();
}
