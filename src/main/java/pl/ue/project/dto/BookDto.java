package pl.ue.project.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private Long availableCopies;
}
