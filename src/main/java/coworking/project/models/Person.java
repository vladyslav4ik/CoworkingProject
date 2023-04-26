package coworking.project.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
@NoArgsConstructor
@Data
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    @Pattern(regexp = "[a-z0-9.]{5,}+@gmail.com", message = "Ваша почтова адреса повинна відповідати патерну: " +
            " тільки літери a-z, цифри 0-9 та . + @gmail.com")
    private String email;

    @NotEmpty(message = "Ім`я користувача не може бути порожнім")
    @Size(min = 2, max = 100, message = "Довжина імені повинна бути у проміжку від 2 до 100 символів")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    //@Size(min = 8, max = 16, message = "Довжина паролю повинна бути у проміжку від 8 до 16 символів")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "year_of_birth")
    @Min(value = 1915, message = "Рік народження повинен бути більшим за 1915")
    private Integer yearOfBirth;
}