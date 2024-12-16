import content.Content;
import html.HTMLGenerator;
import moviedbapi.http.MoviedbApiClient;
import moviedbapi.json.MoviedbJsonParser;
import moviedbapi.json.TvShowJsonParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final String API_KEY = "";
        final String REQUEST_URL = "https://api.themoviedb.org/3/movie/popular";

        final String REQUEST_URL_SERIES = "https://api.themoviedb.org/3/tv/popular";

        String responseMovies = new MoviedbApiClient(API_KEY, REQUEST_URL).get();

        List<Content> moviesListMovieDB = new MoviedbJsonParser().parse(responseMovies);
        moviesListMovieDB.sort(Comparator.comparing(Content::getTitle));

        PrintWriter writerMovieDB = new PrintWriter("content-movie-db.html");
        new HTMLGenerator(writerMovieDB, "Filmes Populares").generate(moviesListMovieDB);
        writerMovieDB.close();

        String responseSeries = new MoviedbApiClient(API_KEY, REQUEST_URL_SERIES).get();
        List<Content> seriesList = new TvShowJsonParser().parse(responseSeries);

        seriesList.sort(Comparator.comparing(Content::getTitle));

        PrintWriter writerTvShowDB = new PrintWriter("content-tv-show-db.html");
        new HTMLGenerator(writerTvShowDB,  "Series Populares").generate(seriesList);
        writerTvShowDB.close();
    }
}



