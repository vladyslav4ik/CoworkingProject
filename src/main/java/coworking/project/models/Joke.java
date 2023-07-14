package coworking.project.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Joke {
    private String setup;
    private String punchline;
}