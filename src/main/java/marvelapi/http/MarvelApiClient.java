package marvelapi.http;

import marvelapi.utils.HashMd5;
import interfaces.ClientAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MarvelApiClient implements ClientAPI {
    private final String API_KEY;
    private final String PRIVATE_KEY;
    private final String REQUEST_URL;

    public MarvelApiClient(String REQUEST_URL,String API_KEY, String PRIVATE_KEY){
        this.REQUEST_URL = REQUEST_URL;
        this.API_KEY = API_KEY;
        this.PRIVATE_KEY = PRIVATE_KEY;
    }

    public String getFetchURL(){
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash = HashMd5.getHashMd5(timestamp + PRIVATE_KEY + this.API_KEY);
        return String.format(this.REQUEST_URL.concat("?ts=%s&apikey=%s&hash=%s"), timestamp, this.API_KEY, hash);
    }

    @Override
    public String get() {
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(getFetchURL()))
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        }catch (IOException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}

