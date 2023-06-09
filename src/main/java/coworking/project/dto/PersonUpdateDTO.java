package coworking.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonUpdateDTO {
    @NotEmpty
    @Size(min = 2, max = 100)
    private String username;
    @Size(min = 8)
    private String password;
    @Email
    @NotEmpty
    private String email;
    @Min(value = 1915)
    private Integer yearOfBirth;
}