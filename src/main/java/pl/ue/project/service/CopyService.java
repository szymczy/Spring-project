package pl.ue.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ue.project.data.Book;
import pl.ue.project.data.Copy;
import pl.ue.project.data.CopyStatus;
import pl.ue.project.dto.CopyDto;
import pl.ue.project.repository.BookRepository;
import pl.ue.project.repository.CopyRepository;
import pl.ue.project.repository.ReaderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CopyService {
    private final CopyRepository copyRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public List<CopyDto> findByBook(Long bookId, CopyStatus status) {
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return copyRepository.findAllByBookAndStatus(book, status)
                .stream()
                .map(this::mapCopyToDto)
                .collect(Collectors.toList());
    }

    public CopyDto findCopy(Long id) {
        return copyRepository.findById(id)
                .map(this::mapCopyToDto)
                .orElseThrow(() -> new RuntimeException("Copy not found"));
    }

    public CopyDto saveCopy(Long bookId) {
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return mapCopyToDto(copyRepository.save(Copy.builder()
                .book(book)
                .status(CopyStatus.AVAILABLE)
                .build()));
    }

    public CopyDto updateCopy(Long id, CopyDto copyDto) {
        final Copy copy = copyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Copy not found"));
        copy.setBorrowingDate(copyDto.getBorrowingDate());
        copy.setExpectedReturnDate(copyDto.getExpectedReturnDate());
        if (copyDto.getReaderId() != null) {
            copy.setReader(readerRepository.findById(copyDto.getReaderId())
                    .orElseThrow(() -> new RuntimeException("Reader not found")));
        } else {
            copy.setReader(null);
        }
        copy.setStatus(CopyStatus.valueOf(copyDto.getStatus()));
        return mapCopyToDto(copyRepository.save(copy));
    }

    public void deleteCopy(Long id) {
        copyRepository.deleteById(id);
    }

    private CopyDto mapCopyToDto(Copy copy) {
        final CopyDto.CopyDtoBuilder builder = CopyDto.builder()
                .id(copy.getId())
                .bookId(copy.getBook().getId())
                .bookTitle(copy.getBook().getTitle())
                .borrowingDate(copy.getBorrowingDate())
                .expectedReturnDate(copy.getExpectedReturnDate())
                .status(copy.getStatus().toString());
        if (copy.getReader() != null) {
            builder.readerId(copy.getId());
        }
        return builder.build();
    }
}
