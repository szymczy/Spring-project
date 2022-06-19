package pl.ue.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ue.project.data.Copy;
import pl.ue.project.data.Reader;
import pl.ue.project.dto.ReaderDto;
import pl.ue.project.repository.ReaderRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderService {
    final ReaderRepository readerRepository;

    public List<ReaderDto> findAll() {
        return readerRepository.findAll()
                .stream()
                .map(this::mapReaderToDto)
                .collect(Collectors.toList());
    }

    public ReaderDto findReader(Long id) {
        return readerRepository.findById(id)
                .map(this::mapReaderToDto)
                .orElseThrow(() -> new RuntimeException("Reader not found"));
    }

    public ReaderDto createReader(ReaderDto readerDto) {
        return mapReaderToDto(readerRepository.save(Reader.builder()
                .firstName(readerDto.getFirstName())
                .lastName(readerDto.getLastName())
                .borrowedCopies(Collections.emptyList())
                .build()));
    }

    public ReaderDto updateReader(Long id, ReaderDto readerDto) {
        final Reader reader = readerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reader not found"));
        reader.setFirstName(readerDto.getFirstName());
        reader.setLastName(readerDto.getLastName());
        return mapReaderToDto(readerRepository.save(reader));
    }

    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }

    private ReaderDto mapReaderToDto(Reader reader) {
        return ReaderDto.builder()
                .id(reader.getId())
                .firstName(reader.getFirstName())
                .lastName(reader.getLastName())
                .borrowedCopiesIds(reader.getBorrowedCopies()
                        .stream()
                        .map(Copy::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
