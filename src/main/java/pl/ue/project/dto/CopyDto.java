package pl.ue.project.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CopyDto {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private Long readerId;
    private LocalDate borrowingDate;
    private LocalDate expectedReturnDate;
    private String status;
}
