package pl.ue.project.data;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name="copy_id")
    private Book book;
    @ManyToOne()
    private Reader reader;
    private LocalDate borrowingDate;
    private LocalDate expectedReturnDate;
    private CopyStatus status;
}

