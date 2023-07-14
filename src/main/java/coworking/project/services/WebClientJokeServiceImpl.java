package coworking.project.services;

import coworking.project.models.Joke;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientJokeServiceImpl implements JokeService{
    private final WebClient webClient;

    public WebClientJokeServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Joke getJoke() {
        Mono<Joke> joke = webClient.method(HttpMethod.GET)
                .header("X-RapidAPI-Host", "joke110.p.rapidapi.com")
                .header("X-RapidAPI-Key", "8fb67bd7c6msh7c322a53e64d643p12a76djsn76b14cbe8856")
                .retrieve()
                .bodyToMono(Joke.class);
        return joke.block();
    }
}
