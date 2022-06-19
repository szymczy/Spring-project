package pl.ue.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ue.project.data.Book;
import pl.ue.project.data.Copy;
import pl.ue.project.data.CopyStatus;
import pl.ue.project.data.Reader;
import pl.ue.project.repository.BookRepository;
import pl.ue.project.repository.CopyRepository;
import pl.ue.project.repository.ReaderRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowingService {
    private final ReaderRepository readerRepository;
    private final CopyRepository copyRepository;
    private final BookRepository bookRepository;

    public long orderABook(Long readerId, Long bookId) {
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("No book found"));
        final Copy copy = book.getCopies()
                .stream()
                .filter(i -> i.getStatus() == CopyStatus.AVAILABLE)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No available copies"));
        final Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new RuntimeException("No such user found."));
        copy.setReader(reader);
        copy.setStatus(CopyStatus.PREPARING);
        copy.setBorrowingDate(LocalDate.now());
        copy.setExpectedReturnDate(LocalDate.now().plusDays(14L));
        return copyRepository.save(copy).getId();
    }

    public void pickUpACopy(Long copyId) {
        final Copy copy = copyRepository.findById(copyId)
                .orElseThrow(() -> new RuntimeException("No such copy found"));
        if (copy.getStatus() != CopyStatus.READY_TO_PICK_UP) {
            throw new RuntimeException("Book is not ready");
        }
        copy.setStatus(CopyStatus.BORROWED);
        copyRepository.save(copy);
    }

    public void returnACopy(Long copyId) {
        final Copy copy = copyRepository.findById(copyId)
                .orElseThrow(() -> new RuntimeException("No such copy found"));
        if (copy.getStatus() != CopyStatus.BORROWED && copy.getStatus() != CopyStatus.OVERDUE) {
            throw new RuntimeException("Wrong copy status");
        }
        copy.setReader(null);
        copy.setBorrowingDate(null);
        copy.setExpectedReturnDate(null);
        copy.setStatus(CopyStatus.AVAILABLE);
        copyRepository.save(copy);
    }
}
