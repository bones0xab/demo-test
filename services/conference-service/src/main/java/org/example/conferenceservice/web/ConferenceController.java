package org.example.conferenceservice.web;


import jakarta.validation.Valid;
import org.example.conferenceservice.dtos.RequesrtConferenceDto;
import org.example.conferenceservice.dtos.ResponseConferenceDto;
import org.example.conferenceservice.services.ConferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/conferences")
public class ConferenceController {

    private final ConferenceService conferenceService;

    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping
    public List<ResponseConferenceDto> getAllConferences(){
        return conferenceService.getAllConferences();
    }

    @GetMapping("/{id}")
    public  ResponseConferenceDto getConferenceById(@PathVariable Long id){
        return conferenceService.getConferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseConferenceDto createConference(@Valid @RequestBody RequesrtConferenceDto requestDto) {
        return conferenceService.createConference(requestDto);
    }

    @PutMapping("/{id}")
    public ResponseConferenceDto updateConference(@PathVariable Long id, @Valid @RequestBody RequesrtConferenceDto requestDto) {
        return conferenceService.updateConference(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConference(@PathVariable Long id) {
        conferenceService.deleteConference(id);
    }

}