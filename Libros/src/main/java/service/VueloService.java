package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Vuelo;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class VueloService {
    private static final String BASE_URL = "http://10.40.15.229:8080/vuelos";
    private final Gson gson = new Gson();

    public List<Vuelo> buscar(String origen, String destino, String fecha) {
        try {
            String url = String.format("%s?origen=%s&destino=%s&fecha=%s", BASE_URL, origen, destino, fecha);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Type listType = new TypeToken<List<Vuelo>>(){}.getType();
                return gson.fromJson(response.body(), listType);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar vuelos: " + e.getMessage());
        }
        return List.of();
    }
}
