package coworking.project.dto;

import coworking.project.models.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PersonDTO {
    private Long id;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String username;

    @Size(min = 8)
    private String password;

    private String role;

    @Min(value = 1915)
    @NotNull
    private Integer yearOfBirth;

    private List<Reservation> reservations;
}