package coworking.project.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "Drink")
@NoArgsConstructor
public class Drink {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name of drink can not be empty!")
    @Size(min = 2, max = 100, message = "The length of name should be in range of 2 and 100!")
    private String name;

    @Column(name = "price")
    @Min(value = 0, message = "Price can not be equal 0 or smaller!")
    private Float price;

    @Column(name = "ingredients")
    @NotEmpty(message = "Drink has to contain ingredients!")
    private String ingredients;
}