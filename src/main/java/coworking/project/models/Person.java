package coworking.project.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "PERSON")
@NoArgsConstructor
@Data
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

    @Min(value = 1915)
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;
}