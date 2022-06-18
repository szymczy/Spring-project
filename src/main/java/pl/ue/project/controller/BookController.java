package pl.ue.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ue.project.service.BookService;

@RestController
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;
}
