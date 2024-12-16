package marvelapi.json;

import content.Content;
import marvelapi.mapper.MarvelResponseMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import interfaces.Parser;

import java.util.ArrayList;
import java.util.List;

public class MarvelJsonParser implements Parser<Content> {
    private final Gson gson;

    public MarvelJsonParser(){
        this.gson = new Gson();
    }

    @Override
    public List<Content> parse(String content){
        JsonObject moviesAsJson = gson.fromJson(content, JsonObject.class);
        JsonArray moviesAsArray = moviesAsJson.get("data").getAsJsonObject().getAsJsonArray("results");

        List<Content> movieList = new ArrayList<>();

        for(JsonElement element : moviesAsArray){
            JsonObject movieAsJson = element.getAsJsonObject();
            Content movie = new MarvelResponseMapper().mapResponse(movieAsJson);
            movieList.add(movie);
        }

        return movieList;
    }
}
