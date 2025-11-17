package org.example.keynoteservice.repositories;

import org.example.keynoteservice.entities.Keynote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeynoteRepo extends JpaRepository<Keynote,Long> {
}
