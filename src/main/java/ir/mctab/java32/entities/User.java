package ir.mctab.java32.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="username", length = 50)
    private String userName;
    @Column(name = "nationalcode", length = 50)
    private String nationalCode;
    @Column(name="birthday")
    @Type(type="date")
    private Date birthday;
    @Column(name="password")
    private String password;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Article> articleList;

    public User(String username, String password, String nationalCode, Date birthDate) {
        this.userName=username;
        this.password=password;
        this.nationalCode=nationalCode;
        this.birthday=birthDate;
    }
}
