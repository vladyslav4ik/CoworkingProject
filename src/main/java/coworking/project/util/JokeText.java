package coworking.project.util;

import coworking.project.models.Joke;

public class JokeText {
    public static String getJokeText(Joke joke) {
        return joke.getSetup() + "\n" + joke.getPunchline();
    }
}
