package org.example.conferenceservice.repositories;

import org.example.conferenceservice.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepo extends JpaRepository<Conference,Long> {
}
