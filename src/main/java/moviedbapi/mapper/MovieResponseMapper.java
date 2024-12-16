package moviedbapi.mapper;

import com.google.gson.JsonObject;
import content.Content;
import interfaces.Mapper;

public class MovieResponseMapper implements Mapper<Content> {
    @Override
    public Content mapResponse(JsonObject root){
        Content content = new Content();

        content.setTitle(root.get("title").getAsString());
        content.setPosterImage("https://image.tmdb.org/t/p/original/" + root.get("poster_path").getAsString());
        content.setRating(root.get("vote_average").getAsDouble());
        content.setReleaseDate(root.get("release_date").getAsString());

        return content;
    }
}
