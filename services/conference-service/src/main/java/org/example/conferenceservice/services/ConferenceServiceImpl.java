package org.example.conferenceservice.services;

import org.example.conferenceservice.dtos.RequesrtConferenceDto;
import org.example.conferenceservice.dtos.ResponseConferenceDto;
import org.example.conferenceservice.entities.Conference;
import org.example.conferenceservice.exceptions.ConferenceNotFoundException;
import org.example.conferenceservice.mappers.ConferenceMapper;
import org.example.conferenceservice.repositories.ConferenceRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceMapper conferenceMapper;
    private final ConferenceRepo conferenceRepo;


    public ConferenceServiceImpl(ConferenceMapper conferenceMapper, ConferenceRepo conferenceRepo) {
        this.conferenceMapper = conferenceMapper;
        this.conferenceRepo = conferenceRepo;
    }


    @Override
    public ResponseConferenceDto createConference(RequesrtConferenceDto requesrtConferenceDto) {
        Conference conference = conferenceMapper.requestDtoToEntity(requesrtConferenceDto);
        return conferenceMapper.entitytoResponseDTO(conferenceRepo.save(conference));
    }

    @Override
    public ResponseConferenceDto getConferenceById(Long id) {
        Conference conference = conferenceRepo.findById(id).orElseThrow(() -> new ConferenceNotFoundException("ID introuvable: " + id));;
        return conferenceMapper.entitytoResponseDTO(conference);
    }

    @Override
    public List<ResponseConferenceDto> getAllConferences() {
        List<Conference> conferences = conferenceRepo.findAll();
        return conferences.stream()
                .map(conferenceMapper::entitytoResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseConferenceDto updateConference(Long conferenceId, RequesrtConferenceDto requesrtConferenceDto) {
        // 1. D'abord, trouver l'entité existante.
        Conference existingConference = conferenceRepo.findById(conferenceId).orElseThrow(()-> new ConferenceNotFoundException("ID introuvable: " + conferenceId));

        existingConference.setTitre(requesrtConferenceDto.getTitre());
        existingConference.setType(requesrtConferenceDto.getType());
        existingConference.setDate(requesrtConferenceDto.getDate());
        existingConference.setDuree(requesrtConferenceDto.getDuree());

        Conference updatedConference = conferenceRepo.save(existingConference);

        return conferenceMapper.entitytoResponseDTO(updatedConference);
    }

    @Override
    public void deleteConference(Long id) {
        conferenceRepo.deleteById(id);
    }
}
