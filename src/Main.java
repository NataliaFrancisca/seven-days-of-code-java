import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final String API_KEY = "";
        final String REQUEST_URL = "https://api.themoviedb.org/3/movie/popular";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(REQUEST_URL))
                .header("accept", "application/json")
                .header("Authorization", "Bearer ".concat(API_KEY))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body:" + response.body());
    }
}
