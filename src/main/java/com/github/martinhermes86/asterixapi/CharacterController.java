package com.github.martinhermes86.asterixapi;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix/characters")
@RequiredArgsConstructor
@Document("charactersOfAsterix")
public class CharacterController {

    private final CharacterRepo characterRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Character createCharacter(@RequestBody Character character) {
        return characterRepo.save(character);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Character> createCharacter(@RequestBody List<Character> character) {
        return characterRepo.saveAll(character);
    }

    @GetMapping()
    public List<Character> getCharacters() {
        return characterRepo.findAll();
    }

    @GetMapping("/{id}")
    public Character getCharacter(@PathVariable String id) {
        return characterRepo.findById(id).orElseThrow();
    }

    @GetMapping("/search")
    public List<Character> searchCharacter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String occupation) {
        return characterRepo.findByNameOrAgeOrOccupation(name, age, occupation);
    }

    @PutMapping("/{id}")
    public Character updateCharacter(@PathVariable String id, @RequestBody Character character) {
        Character existingCharacter = getCharacter(id);
        return characterRepo.save(existingCharacter
                .withName(character.name())
                .withAge(character.age())
                .withOccupation(character.occupation()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharacter(@PathVariable String id) {
        characterRepo.deleteById(id);
    }

    @DeleteMapping("/bulk")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllCharacters() {
        characterRepo.deleteAll();
    }
}
