import html.HTMLGenerator;
import MarvelAPI.http.MarvelApiClient;
import MoviedbAPI.http.MoviedbApiClient;
import MarvelAPI.json.MarvelJsonParser;
import MoviedbAPI.json.MoviedbJsonParser;
import movie.Movie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final String API_KEY = "";
        final String REQUEST_URL = "https://api.themoviedb.org/3/movie/popular";

        final String PUBLIC_API_KEY = "";
        final String PRIVATE_API_KEY = "";
        final String MARVEL_REQUEST_URL = "https://gateway.marvel.com/v1/public/series";

        String responseMovies = new MoviedbApiClient(API_KEY, REQUEST_URL).get();
        List<Movie> moviesListMovieDB = new MoviedbJsonParser().parse(responseMovies);

        PrintWriter writerMovieDB = new PrintWriter("content-movie-db.html");
        new HTMLGenerator(writerMovieDB).generate(moviesListMovieDB);
        writerMovieDB.close();

        String responseMarvel = new MarvelApiClient(MARVEL_REQUEST_URL, PUBLIC_API_KEY, PRIVATE_API_KEY).get();
        List<Movie> moviesListMarvel = new MarvelJsonParser().parse(responseMarvel);

        PrintWriter writerMarvel = new PrintWriter("content-marvel.html");
        new HTMLGenerator(writerMarvel).generate(moviesListMarvel);
        writerMarvel.close();
    }
}



