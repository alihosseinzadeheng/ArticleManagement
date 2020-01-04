package ir.mctab.java32.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity @ToString
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title", length = 50)
    private String title;

    @Column(name="brief", length = 100)
    private String brief;

    @Column(name="content", length = 100)
    private String content;

    @Column(name="creteDate")
    private Date createDate;

    @Column(name="lastupdatedate")
    private Date lastUpdateDate;

    @Column(name="publishdate")
    private Date publishDate;

    @Column(name="ispublished")
    private Boolean isPublished;

    @ManyToOne
    @ToString.Exclude
    private Category category;
//cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER
    @ManyToOne
    @ToString.Exclude
    private User user;

}
