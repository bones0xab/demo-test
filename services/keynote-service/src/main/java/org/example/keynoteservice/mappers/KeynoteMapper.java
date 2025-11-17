package org.example.keynoteservice.mappers;

import org.example.keynoteservice.dtos.RequestKeynoteDto;
import org.example.keynoteservice.dtos.ResponseKeynoteDto;
import org.example.keynoteservice.entities.Keynote;
import org.example.keynoteservice.repositories.KeynoteRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class KeynoteMapper {
   private final ModelMapper modelMapper;

   public KeynoteMapper(ModelMapper modelMapper) {
         this.modelMapper = modelMapper;
   }

   public Keynote dtoToentity(RequestKeynoteDto dto) {
       return modelMapper.map(dto, Keynote.class);
   }

   public ResponseKeynoteDto entitytoResponseDTO(Keynote keynote) {
       return modelMapper.map(keynote, ResponseKeynoteDto.class);
   }

}
