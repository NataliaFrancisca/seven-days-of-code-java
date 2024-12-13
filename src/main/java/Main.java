import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import html.HTMLGenerator;
import movie.Movie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final String API_KEY = "";
        final String REQUEST_URL = "https://api.themoviedb.org/3/movie/popular";

        // FAZENDO A REQUISIÇÃO NA API DE FILMES
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(REQUEST_URL))
                .header("accept", "application/json")
                .header("Authorization", "Bearer ".concat(API_KEY))
                .GET()
                .build();

        // RECEBENDO A RESPOSTA DA REQUISIÇÃO
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // USANDO O GSON PARA ACESSAR AS INFORMAÇÕES EM FORMATO JSON
        Gson gson = new Gson();

        JsonObject jsonMoviesObject = gson.fromJson(response.body(), JsonObject.class);
        JsonArray movies = jsonMoviesObject.getAsJsonArray("results");

        List<String> titles = new ArrayList<>();
        List<String> imgPath = new ArrayList<>();

        for(JsonElement movie : movies){
            String movieTitle = getAttribute(movie, "original_title");
            String movieImage = getAttribute(movie, "poster_path");

            titles.add(movieTitle);
            imgPath.add(movieImage);
        }

        // CRIANDO UMA LISTA QUE RECEBE OS FILMES USANDO A CLASSE MOVIE
        List<Movie> movieList = gson.fromJson(movies.toString(), new TypeToken<List<Movie>>() {}.getType());

        PrintWriter writer = new PrintWriter("content.html");
        new HTMLGenerator(writer).generate(movieList);
        writer.close();
    }

    public static String getAttribute(JsonElement movie, String attribute){
        return movie.getAsJsonObject().get(attribute).getAsString();
    }
}
