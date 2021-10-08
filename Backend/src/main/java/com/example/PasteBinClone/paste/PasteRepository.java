package com.example.PasteBinClone.paste;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasteRepository extends JpaRepository <Paste, Long> {

        @Query("SELECT p FROM Paste p WHERE p.title = ?1")
        Optional<Paste> findPastebyTitle(String title);

        @Query("SELECT p FROM Paste p WHERE p.user = ?1")
        List<Paste> findPastesByUser(String user);

        @Query("SELECT p FROM Paste p WHERE p.id = ?1")
        Optional<Paste> findPasteById(Long pasteId);
}