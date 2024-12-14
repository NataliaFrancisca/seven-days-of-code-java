package MoviedbAPI.mapper;

import com.google.gson.JsonObject;
import interfaces.Mapper;
import movie.Movie;

public class MoviedbResponseMapper implements Mapper<Movie> {

    @Override
    public Movie mapResponse(JsonObject root){
        Movie movie = new Movie();
        movie.setTitle(root.get("title").getAsString());
        movie.setPosterImage("https://image.tmdb.org/t/p/original/" + root.get("poster_path").getAsString());
        movie.setRating(root.get("vote_average").getAsDouble());
        movie.setReleaseDate(root.get("release_date").getAsString());

        return movie;
    }
}
