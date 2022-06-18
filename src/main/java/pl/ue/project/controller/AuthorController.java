package pl.ue.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ue.project.dto.AuthorDto;
import pl.ue.project.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping(value = "/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @GetMapping
    public List<AuthorDto> getAllAuthor() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id) {
        final AuthorDto author = service.findAuthor(id);
        return ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<AuthorDto> crateAuthor(@RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(service.saveAuthor(authorDto));
    }

    @PutMapping("/{id}")
    public AuthorDto updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        return service.updateAuthor(id, authorDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        service.deleteAuthor(id);
    }
}
