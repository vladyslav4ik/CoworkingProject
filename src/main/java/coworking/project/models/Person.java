package coworking.project.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PERSON")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotEmpty
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty
    @Size(min = 2, max = 100)
    @Column(name = "username", unique = true)
    private String username;

    @Size(min = 8)
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @NotNull
    @Min(value = 1915)
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;

    @OneToMany(mappedBy = "renter", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}