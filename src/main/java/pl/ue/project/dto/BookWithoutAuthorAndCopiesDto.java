package pl.ue.project.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookWithoutAuthorAndCopiesDto {
    private Long id;
    private String title;
    private LocalDate datePublished;
}
