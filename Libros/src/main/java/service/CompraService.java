package service;

import com.google.gson.Gson;
import model.Compra;
import model.Vuelo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class CompraService {
    private static final String BASE_URL = "http://10.40.15.229:8080/compras";
    private final Gson gson = new Gson();

    public void realizarCompra(String nombreComprador, Vuelo vuelo) {
        try {
            Compra compra = new Compra();
            compra.setComprador(nombreComprador);
            compra.setVuelo(vuelo);
            compra.setFechaCompra(LocalDateTime.now().toString());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(compra)))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200 || response.statusCode() == 201) {
                System.out.println("✅ Compra registrada con éxito.");
            } else {
                System.out.println("❌ Error al registrar compra.");
            }
        } catch (Exception e) {
            System.out.println("Error en la compra: " + e.getMessage());
        }
    }
}
