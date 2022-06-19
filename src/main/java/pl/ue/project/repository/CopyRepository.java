package pl.ue.project.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ue.project.data.Book;
import pl.ue.project.data.Copy;
import pl.ue.project.data.CopyStatus;

import java.time.LocalDate;
import java.util.List;

public interface CopyRepository extends CrudRepository<Copy, Long> {
    List<Copy> findAll();

    List<Copy> findAllByBookAndStatus(Book book, CopyStatus status);

    List<Copy> findAllByStatus(CopyStatus status);

    List<Copy> findAllByStatusAndExpectedReturnDateLessThan(CopyStatus status, LocalDate date);
}
