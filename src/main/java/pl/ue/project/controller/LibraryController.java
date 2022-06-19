package pl.ue.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ue.project.service.BorrowingService;

@RestController
@RequestMapping(value = "/library")
@RequiredArgsConstructor
public class LibraryController {
    private final BorrowingService service;

    @GetMapping("borrow/{readerId}/{bookId}")
    public ResponseEntity<?> borrowABook(@PathVariable Long readerId, @PathVariable Long bookId) {
        final Long copyId = service.orderABook(readerId, bookId);
        return ResponseEntity.ok(copyId);
    }

    @GetMapping("pickUp/{copyId}")
    public ResponseEntity<?> pickUpACopy(@PathVariable Long copyId) {
        service.pickUpACopy(copyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("return/{copyId}")
    public ResponseEntity<?> returnACopy(@PathVariable Long copyId) {
        service.returnACopy(copyId);
        return ResponseEntity.ok().build();
    }
}
