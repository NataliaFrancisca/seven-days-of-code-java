package moviedbapi.http;

import interfaces.ClientAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MoviedbApiClient implements ClientAPI {
    private final String API_KEY;
    private final String REQUEST_URL;

    public MoviedbApiClient(String API_KEY, String REQUEST_URL){
        this.API_KEY = API_KEY;
        this.REQUEST_URL = REQUEST_URL;
    }

    public String get(){
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.REQUEST_URL))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer ".concat(API_KEY))
                    .GET()
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        }catch (IOException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
