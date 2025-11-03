package TestingApp.TestingApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WebClient webClient;

    public WeatherController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://weather.googleapis.com/v1/currentConditions:lookup").build();
    }

    @GetMapping
    public String getWeather() {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("key", "AIzaSyAx4ae4IapN9Ep2TJfEUQs43TbIdUNgnx8")
                        .queryParam("location.latitude", "51.43891535129689")
                        .queryParam("location.longitude", "5.478145355257845")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
