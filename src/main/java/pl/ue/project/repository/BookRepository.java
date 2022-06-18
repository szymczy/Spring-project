package pl.ue.project.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ue.project.data.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAll();
}
