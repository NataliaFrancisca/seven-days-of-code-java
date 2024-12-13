package json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import movie.Movie;

import java.util.List;

public class MovieJsonParser {
    private final Gson gson;

    public MovieJsonParser(){
        this.gson = new Gson();
    }

    public List<Movie> parse(String movies){
        JsonObject jsonObject = gson.fromJson(movies, JsonObject.class);
        JsonArray moviesAsArray = jsonObject.getAsJsonArray("results");
        return gson.fromJson(moviesAsArray.toString(), new TypeToken<List<Movie>>() {}.getType());
    }
}
