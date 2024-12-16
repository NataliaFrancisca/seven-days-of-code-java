package moviedbapi.json;

import content.Content;
import moviedbapi.mapper.MovieResponseMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import interfaces.Parser;

import java.util.ArrayList;
import java.util.List;

public class MoviedbJsonParser implements Parser<Content> {
    private final Gson gson;

    public MoviedbJsonParser(){
        this.gson = new Gson();
    }

    @Override
    public List<Content> parse(String movies){
        JsonObject moviesAsJson = gson.fromJson(movies, JsonObject.class);
        JsonArray moviesAsArray =  moviesAsJson.getAsJsonArray("results");

        List<Content> movieList = new ArrayList<>();

        for(JsonElement element : moviesAsArray){
            JsonObject movieAsJson = element.getAsJsonObject();
            Content movie = new MovieResponseMapper().mapResponse(movieAsJson);
            movieList.add(movie);
        }

        return movieList;
    }
}
