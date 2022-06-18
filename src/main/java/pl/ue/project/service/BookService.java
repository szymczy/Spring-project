package pl.ue.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ue.project.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
}
