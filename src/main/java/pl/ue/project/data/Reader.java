package pl.ue.project.data;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "reader")
    private List<Copy> borrowedCopies;
}
