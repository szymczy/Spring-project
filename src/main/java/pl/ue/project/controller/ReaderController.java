package pl.ue.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ue.project.dto.ReaderDto;
import pl.ue.project.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping(value = "/reader")
@RequiredArgsConstructor
public class ReaderController {
    private final ReaderService service;

    @GetMapping
    public List<ReaderDto> getAllReaders() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ReaderDto getReader(@PathVariable Long id) {
        return service.findReader(id);
    }

    @PostMapping
    public ReaderDto crateReader(@RequestBody ReaderDto readerDto) {
        return service.createReader(readerDto);
    }

    @PutMapping("/{id}")
    public ReaderDto updateReader(@PathVariable Long id, @RequestBody ReaderDto readerDto) {
        return service.updateReader(id, readerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable Long id) {
        service.deleteReader(id);
    }
}
