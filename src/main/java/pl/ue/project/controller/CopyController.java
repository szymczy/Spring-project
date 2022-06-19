package pl.ue.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ue.project.data.CopyStatus;
import pl.ue.project.dto.CopyDto;
import pl.ue.project.service.CopyService;

import java.util.List;

@RestController
@RequestMapping(value = "/copy")
@RequiredArgsConstructor
public class CopyController {
    private final CopyService copyService;

    @GetMapping("/book/{bookId}/{status}")
    public List<CopyDto> getAllCopiesByBookAndStatus(@PathVariable Long bookId,
                                                     @PathVariable CopyStatus status) {
        return copyService.findByBook(bookId, status);
    }

    @GetMapping("/{id}")
    public CopyDto getCopy(@PathVariable Long id) {
        return copyService.findCopy(id);
    }

    @PostMapping("/{bookId}")
    public CopyDto crateCopy(@PathVariable Long bookId) {
        return copyService.saveCopy(bookId);
    }

    @PutMapping("/{id}")
    public CopyDto updateCopy(@PathVariable Long id, @RequestBody CopyDto copyDto) {
        return copyService.updateCopy(id, copyDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCopy(@PathVariable Long id) {
        copyService.deleteCopy(id);
    }
}
