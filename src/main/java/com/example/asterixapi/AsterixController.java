package com.example.asterixapi;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/asterix")
public class AsterixController {

    private CharacterRepo characterRepo;

    public AsterixController(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    @GetMapping("/characters")
    public List<Characters> getAllCharacters() {
        return characterRepo.findAll();
    }

    @PostMapping("/characters")
    public Characters addCharacters(@RequestBody Characters characters) {
        Characters newCharacters = new Characters(UUID.randomUUID().toString(), characters.name(), characters.age(), characters.profession());
        return characterRepo.save(newCharacters);
    }

}
