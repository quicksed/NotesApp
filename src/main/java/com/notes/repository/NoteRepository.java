package com.notes.repository;

import com.notes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<List<Note>> findByUserId(Long userId);

    @Query(value = "SELECT * FROM note " +
            "JOIN \"user\" on \"user\".id = note.user_id " +
            "WHERE \"user\".email = ?1", nativeQuery = true)
    Optional<List<Note>> findByUserEmail(String email);

    @Query(value = "SELECT Count(*) FROM note " +
            "JOIN \"user\" on \"user\".id = note.user_id " +
            "WHERE \"user\".email = ?1 " +
            "GROUP BY \"user\"", nativeQuery = true)
    Optional<Integer> findNotesCountByUserEmail(String email);
}
