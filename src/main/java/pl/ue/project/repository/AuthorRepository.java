package pl.ue.project.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ue.project.data.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findAll();
}
