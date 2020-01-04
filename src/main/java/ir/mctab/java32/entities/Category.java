package ir.mctab.java32.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category")
    private List<Article> articleList;

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
