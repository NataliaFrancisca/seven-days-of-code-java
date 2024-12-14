package interfaces;

import com.google.gson.JsonObject;

public interface Mapper<T> {
    T mapResponse(JsonObject root);
}
