package org.example.conferenceservice.mappers;


import org.example.conferenceservice.dtos.RequesrtConferenceDto;
import org.example.conferenceservice.dtos.ResponseConferenceDto;
import org.example.conferenceservice.entities.Conference;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class ConferenceMapper {

    private final ModelMapper modelMapper;

    public ConferenceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Conference requestDtoToEntity(RequesrtConferenceDto dto) {
        Conference conference = modelMapper.map(dto, Conference.class);
        conference.setNbrinscripts(0);
        conference.setScore(0);
        conference.setReviews(new ArrayList<>());
        return conference;
    }


    public ResponseConferenceDto entitytoResponseDTO(Conference conference) {
        return modelMapper.map(conference, ResponseConferenceDto.class);
    }

}
