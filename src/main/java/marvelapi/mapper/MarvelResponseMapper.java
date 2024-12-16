package marvelapi.mapper;

import com.google.gson.JsonObject;
import content.Content;
import interfaces.Mapper;

public class MarvelResponseMapper implements Mapper<Content> {

    @Override
    public Content mapResponse(JsonObject root){
        Content movie = new Content();
        movie.setTitle(root.get("title").getAsString());

        JsonObject thumbnail = root.getAsJsonObject("thumbnail");
        String imageURL = thumbnail.get("path").getAsString() + "." + thumbnail.get("extension").getAsString();
        movie.setPosterImage(imageURL);
        movie.setRating(null);
        movie.setReleaseDate(root.get("startYear").getAsString());

        return movie;
    };
}
