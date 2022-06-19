package pl.ue.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ue.project.dto.BookDto;
import pl.ue.project.service.BookService;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return service.findBook(id);
    }

    @PostMapping("/{authorId}")
    public BookDto crateBook(@PathVariable Long authorId,
                             @RequestBody BookDto bookDto) {
        return service.saveBook(authorId, bookDto);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return service.updateBook(id, bookDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }
}
