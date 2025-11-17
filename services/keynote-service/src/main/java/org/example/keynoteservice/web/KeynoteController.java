package org.example.keynoteservice.web;

import org.example.keynoteservice.dtos.RequestKeynoteDto;
import org.example.keynoteservice.dtos.ResponseKeynoteDto;
import org.example.keynoteservice.services.Keynoteservice; // 1. Votre INTERFACE
import jakarta.validation.Valid; // 2. Pour la validation
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keynotes")
public class KeynoteController {

    private final Keynoteservice keynoteservice;

    public KeynoteController(Keynoteservice keynoteservice) {
        this.keynoteservice = keynoteservice;
    }

    @GetMapping
    public List<ResponseKeynoteDto> getAllKeynotes() {
        return keynoteservice.findAllKeynotes();
    }


    @GetMapping("/{id}")
    public ResponseKeynoteDto getKeynoteById(@PathVariable Long id) {
        return keynoteservice.findKeynoteById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 5. Renvoie un statut 201 (Created)
    public ResponseKeynoteDto createKeynote(@Valid @RequestBody RequestKeynoteDto requestDto) {
        return keynoteservice.createKeynote(requestDto);
    }

    /**
     * PUT /keynotes/{id}
     * Met à jour un keynote existant.
     */
    @PutMapping("/{id}")
    public ResponseKeynoteDto updateKeynote(@PathVariable Long id,
                                            @Valid @RequestBody RequestKeynoteDto requestDto) {
        return keynoteservice.updateKeynote(id, requestDto);
    }

    /**
     * DELETE /keynotes/{id}
     * Supprime un keynote.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 6. Renvoie un statut 204 (No Content)
    public void deleteKeynote(@PathVariable Long id) {
        keynoteservice.deleteKeynote(id);
    }
}