package coworking.project.services;

import coworking.project.models.Joke;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateJokeServiceImpl implements JokeService {
    private final RestTemplate restTemplate;
    private final String URL = "https://joke110.p.rapidapi.com/random_joke";

    public RestTemplateJokeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Joke getJoke() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        headers.add("X-RapidAPI-Key", "8fb67bd7c6msh7c322a53e64d643p12a76djsn76b14cbe8856");
        headers.add("X-RapidAPI-Host", "joke110.p.rapidapi.com");
        ResponseEntity<Joke> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Joke.class);
        return response.getBody();
    }
}
