package wiki.vero.solgit.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GithubApiConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
            .errorHandler(new ResponseErrorHandler() {

                @Override
                public boolean hasError(final ClientHttpResponse response) throws IOException {
                    return response.getStatusCode().is4xxClientError() ||
                        response.getStatusCode().is5xxServerError();
                }

                @Override
                public void handleError(final ClientHttpResponse response) throws IOException {
                    // General error handling, maybe log the error
                    String body = new BufferedReader(new InputStreamReader(response.getBody()))
                        .lines().collect(Collectors.joining("\n"));

                    throw new RuntimeException("Response error: " + response.getStatusCode() + " Body: " + body);
                }
            })
            .build();
    }
}
