package ir.mctab.java32.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "title")
    private String title;

    @ManyToMany(mappedBy = "role",fetch = FetchType.LAZY)
    private Set<User> users=new HashSet<>();
}
