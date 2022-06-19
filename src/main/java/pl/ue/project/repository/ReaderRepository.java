package pl.ue.project.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ue.project.data.Reader;

import java.util.List;

public interface ReaderRepository extends CrudRepository<Reader, Long> {
    List<Reader> findAll();
}
