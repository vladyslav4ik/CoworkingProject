package coworking.project.dto;

import coworking.project.models.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PersonDTO {
    @Id
    private Long id;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String username;

    @Size(min = 8)
    private String password;

    private String role;

    @Min(value = 1915)
    private Integer yearOfBirth;

    private List<Reservation> reservations;
}
