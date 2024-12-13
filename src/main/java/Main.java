import html.HTMLGenerator;
import http.MoviesApiClient;
import json.MovieJsonParser;
import movie.Movie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2YmFjNzg5NmFiY2RjZDJmYTU1MTZlNmQ2Yjg5YmFhMiIsIm5iZiI6MTYyMTY0NjQ5OC40NzMsInN1YiI6IjYwYTg1Y2EyMDM5OGFiMDA3N2M3MDllMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.mdR4tlmhMLFEvpBHS4CV7k6dlFz7xOXm99M7YE3LmPM";
        final String REQUEST_URL = "https://api.themoviedb.org/3/movie/popular";

        String response = new MoviesApiClient(API_KEY, REQUEST_URL).get();
        List<Movie> movieList = new MovieJsonParser().parse(response);

        PrintWriter writer = new PrintWriter("content.html");
        new HTMLGenerator(writer).generate(movieList);
        writer.close();
    }
}
