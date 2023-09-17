package com.example.asterixapi;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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
    @PutMapping("/characters/{id}")
    public Characters changeCharacters(@PathVariable String id, @RequestBody Characters characters) {
        if(!id.equals(characters.id())) {
            throw new IllegalArgumentException("Character ID does not match");
        }
        return characterRepo.save(characters);
    }

    @DeleteMapping("/characters/{id}")
    public void removeCharacters(@PathVariable String id) {
        characterRepo.deleteById(id);
    }
}
