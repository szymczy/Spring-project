package pl.ue.project.data;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;
    @OneToMany(mappedBy = "book")
    private List<Copy> copies;
    private LocalDate datePublished;
}
