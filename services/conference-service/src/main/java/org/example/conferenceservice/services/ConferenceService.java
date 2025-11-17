package org.example.conferenceservice.services;

import org.example.conferenceservice.dtos.*;

import java.util.List;

public interface ConferenceService {

    ResponseConferenceDto createConference(RequesrtConferenceDto requesrtConferenceDto);
    ResponseConferenceDto getConferenceById(Long id);
    List<ResponseConferenceDto> getAllConferences();
    ResponseConferenceDto updateConference(Long conferenceId,RequesrtConferenceDto requesrtConferenceDto);
    void deleteConference(Long id);
}
