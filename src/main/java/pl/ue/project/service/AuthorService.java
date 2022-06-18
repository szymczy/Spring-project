package pl.ue.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ue.project.data.Author;
import pl.ue.project.dto.AuthorDto;
import pl.ue.project.dto.BookWithoutAuthorAndCopiesDto;
import pl.ue.project.repository.AuthorRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;

    public AuthorDto saveAuthor(AuthorDto authorDto) {
        final Author saved = repository.save(Author.builder()
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .build());
        return AuthorDto.builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .books(Collections.emptyList())
                .build();
    }

    public AuthorDto findAuthor(Long id) {
        return repository.findById(id)
                .map(this::mapAuthorToDto)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public List<AuthorDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::mapAuthorToDto)
                .collect(Collectors.toList());
    }

    public AuthorDto updateAuthor(long id, AuthorDto dto) {
        final Author author = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        final Author save = repository.save(author);
        return mapAuthorToDto(save);
    }

    public void deleteAuthor(Long id) {
        repository.deleteById(id);
    }

    private AuthorDto mapAuthorToDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .books(author.getBooks()
                        .stream()
                        .map(b -> BookWithoutAuthorAndCopiesDto.builder()
                                .id(b.getId())
                                .title(b.getTitle())
                                .datePublished(b.getDatePublished())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
