package pl.ue.project.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "copy_id")
    private Book book;
    @ManyToOne()
    private Reader reader;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate borrowingDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedReturnDate;
    private CopyStatus status;
}

