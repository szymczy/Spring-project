package pl.ue.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ue.project.data.Author;
import pl.ue.project.data.Book;
import pl.ue.project.data.CopyStatus;
import pl.ue.project.dto.BookDto;
import pl.ue.project.repository.AuthorRepository;
import pl.ue.project.repository.BookRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookDto saveBook(long authorId, BookDto bookDto) {
        final Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return mapBookToDto(bookRepository.save(Book.builder()
                .title(bookDto.getTitle())
                .author(author)
                .copies(Collections.emptyList())
                .build()));
    }

    public BookDto findBook(Long id) {
        return bookRepository.findById(id)
                .map(this::mapBookToDto)
                .orElseThrow(() -> new RuntimeException("Boo not found"));
    }

    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapBookToDto)
                .collect(Collectors.toList());
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookDto.getTitle());
        return mapBookToDto(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private BookDto mapBookToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorFirstName(book.getAuthor().getFirstName())
                .authorLastName(book.getAuthor().getLastName())
                .availableCopies(book.getCopies().stream()
                        .filter(c -> c.getStatus().equals(CopyStatus.AVAILABLE))
                        .count())
                .build();
    }
}
