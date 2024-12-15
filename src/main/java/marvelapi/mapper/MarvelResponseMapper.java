package marvelapi.mapper;

import com.google.gson.JsonObject;
import interfaces.Mapper;
import movie.Movie;

public class MarvelResponseMapper implements Mapper<Movie> {

    @Override
    public Movie mapResponse(JsonObject root){
        Movie movie = new Movie();
        movie.setTitle(root.get("title").getAsString());

        JsonObject thumbnail = root.getAsJsonObject("thumbnail");
        String imageURL = thumbnail.get("path").getAsString() + "." + thumbnail.get("extension").getAsString();
        movie.setPosterImage(imageURL);
        movie.setRating(null);
        movie.setReleaseDate(root.get("startYear").getAsString());

        return movie;
    };
}
