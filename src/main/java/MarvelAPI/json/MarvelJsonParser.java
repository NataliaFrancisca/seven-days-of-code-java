package MarvelAPI.json;

import MarvelAPI.mapper.MarvelResponseMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import interfaces.Parser;
import movie.Movie;

import java.util.ArrayList;
import java.util.List;

public class MarvelJsonParser implements Parser<Movie> {
    private final Gson gson;

    public MarvelJsonParser(){
        this.gson = new Gson();
    }

    @Override
    public List<Movie> parse(String movies){
        JsonObject moviesAsJson = gson.fromJson(movies, JsonObject.class);
        JsonArray moviesAsArray = moviesAsJson.get("data").getAsJsonObject().getAsJsonArray("results");

        List<Movie> movieList = new ArrayList<>();

        for(JsonElement element : moviesAsArray){
            JsonObject movieAsJson = element.getAsJsonObject();
            Movie movie = new MarvelResponseMapper().mapResponse(movieAsJson);
            movieList.add(movie);
        }

        return movieList;
    }
}
