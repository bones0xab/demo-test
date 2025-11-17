package org.example.keynoteservice.services;


import org.example.keynoteservice.dtos.RequestKeynoteDto;
import org.example.keynoteservice.dtos.ResponseKeynoteDto;
import org.example.keynoteservice.entities.Keynote;
import org.example.keynoteservice.exceptions.KeynoteNotFoundException;
import org.example.keynoteservice.mappers.KeynoteMapper;
import org.example.keynoteservice.repositories.KeynoteRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class KeynoteServiceImpl implements Keynoteservice { // 2. Implémente votre interface

    // 3. Injecter les "outils" (Repo et Mapper)
    private final KeynoteRepo keynoteRepo;
    private final KeynoteMapper keynoteMapper;

    public KeynoteServiceImpl(KeynoteRepo keynoteRepo, KeynoteMapper keynoteMapper) {
        this.keynoteRepo = keynoteRepo;
        this.keynoteMapper = keynoteMapper;
    }

    @Override
    public ResponseKeynoteDto createKeynote(RequestKeynoteDto requestKeynoteDto) {
        // 1. Traduire DTO -> Entité
        Keynote keynote = keynoteMapper.dtoToentity(requestKeynoteDto);
        // (Pour cette entité simple, pas besoin de valeurs par défaut, le mapper suffit)

        // 2. Sauvegarder
        Keynote savedKeynote = keynoteRepo.save(keynote);

        // 3. Traduire Entité -> DTO de Réponse
        return keynoteMapper.entitytoResponseDTO(savedKeynote);
    }

    @Override
    public ResponseKeynoteDto findKeynoteById(Long id) {
        // 1. Trouver ou lever une exception (l'approche pro)
        Keynote keynote = keynoteRepo.findById(id)
                .orElseThrow(() -> new KeynoteNotFoundException("Keynote introuvable avec l'ID: " + id));

        // 2. Mapper et renvoyer
        return keynoteMapper.entitytoResponseDTO(keynote);
    }

    @Override
    public List<ResponseKeynoteDto> findAllKeynotes() {
        // 1. Trouver tout
        List<Keynote> keynotes = keynoteRepo.findAll();

        // 2. Mapper la liste avec un Stream (l'approche pro)
        return keynotes.stream()
                .map(keynoteMapper::entitytoResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseKeynoteDto updateKeynote(Long id, RequestKeynoteDto requestKeynoteDto) {
        // 1. Trouver l'entité existante (lève une exception si non trouvée)
        Keynote existingKeynote = keynoteRepo.findById(id)
                .orElseThrow(() -> new KeynoteNotFoundException("Keynote introuvable avec l'ID: " + id));

        // 2. Mettre à jour les champs de l'entité existante
        existingKeynote.setNom(requestKeynoteDto.getNom());
        existingKeynote.setPrenom(requestKeynoteDto.getPrenom());
        existingKeynote.setEmail(requestKeynoteDto.getEmail());
        existingKeynote.setFonction(requestKeynoteDto.getFonction());

        // 3. Sauvegarder l'entité mise à jour
        Keynote updatedKeynote = keynoteRepo.save(existingKeynote);

        // 4. Renvoyer le DTO
        return keynoteMapper.entitytoResponseDTO(updatedKeynote);
    }

    @Override
    public void deleteKeynote(Long id) {
        // 1. Vérifier si l'ID existe avant de supprimer
        if (!keynoteRepo.existsById(id)) {
            throw new KeynoteNotFoundException("Impossible de supprimer : Keynote introuvable avec l'ID: " + id);
        }

        // 2. Supprimer
        keynoteRepo.deleteById(id);
    }
}