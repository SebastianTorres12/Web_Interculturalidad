package service;

import com.google.gson.Gson;
import model.Usuario;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthService {
    private static final String BASE_URL = "http://10.40.15.229:8080/auth";
    private final Gson gson = new Gson();

    public Usuario login(String correo, String password) {
        try {
            Usuario request = new Usuario();
            request.setCorreo(correo);
            request.setPassword(password);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                var json = gson.fromJson(response.body(), com.google.gson.JsonObject.class);
                Usuario u = new Usuario();
                u.setNombre(json.get("nombre").getAsString());
                u.setRol(json.get("rol").getAsString());
                return u;
            }
        } catch (Exception e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
        return null;
    }
}
