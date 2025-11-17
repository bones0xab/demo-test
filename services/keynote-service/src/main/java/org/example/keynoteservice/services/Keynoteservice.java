package org.example.keynoteservice.services;

import org.example.keynoteservice.dtos.RequestKeynoteDto;
import org.example.keynoteservice.dtos.ResponseKeynoteDto;

import java.util.List;

public interface Keynoteservice {
    ResponseKeynoteDto findKeynoteById(Long id);
    List<ResponseKeynoteDto> findAllKeynotes();
    ResponseKeynoteDto createKeynote(RequestKeynoteDto requestKeynoteDto);
    ResponseKeynoteDto updateKeynote(Long id, RequestKeynoteDto requestKeynoteDto);
    void deleteKeynote(Long id);
}
