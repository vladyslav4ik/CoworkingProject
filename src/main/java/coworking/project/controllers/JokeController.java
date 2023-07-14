package coworking.project.controllers;

import coworking.project.services.RestTemplateJokeServiceImpl;
import coworking.project.services.WebClientJokeServiceImpl;
import coworking.project.util.JokeText;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jokes")
public class JokeController {
    private final WebClientJokeServiceImpl webClientJokeService;
    private final RestTemplateJokeServiceImpl restTemplateJokeService;

    public JokeController(WebClientJokeServiceImpl webClientJokeService, RestTemplateJokeServiceImpl restTemplateJokeService) {
        this.webClientJokeService = webClientJokeService;
        this.restTemplateJokeService = restTemplateJokeService;
    }

    @GetMapping("/rest")
    public ResponseEntity<String> getRestJoke() {
        String joke = JokeText.getJokeText(restTemplateJokeService.getJoke());
        return new ResponseEntity<>(joke, HttpStatus.OK);
    }

    @GetMapping("/web")
    public ResponseEntity<String> getWebClientJoke() {
        String joke = JokeText.getJokeText(webClientJokeService.getJoke());
        return new ResponseEntity<>(joke, HttpStatus.OK);
    }
}