package moviedbapi.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import content.Content;
import interfaces.Parser;
import moviedbapi.mapper.TvShowResponseMapper;

import java.util.ArrayList;
import java.util.List;

public class TvShowJsonParser implements Parser<Content> {
    private final Gson gson;

    public TvShowJsonParser(){
        this.gson = new Gson();
    }

    @Override
    public List<Content> parse(String shows){
        JsonObject showsAsJson = gson.fromJson(shows, JsonObject.class);
        JsonArray showsAsArray =  showsAsJson.getAsJsonArray("results");

        List<Content> showsList = new ArrayList<>();

        for(JsonElement element: showsAsArray){
            JsonObject showAsJson = element.getAsJsonObject();
            Content show = new TvShowResponseMapper().mapResponse(showAsJson);
            showsList.add(show);
        }

        return showsList;
    }
}
