package br.com.alura.Literalura.apirequest;

import br.com.alura.Literalura.dto.LivroDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class GutendexClient {

    private static final String BASE_URL = "https://gutendex.com/books/";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GutendexClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<LivroDTO> buscarLivroPorTitulo(String titulo) {
        try {
            String url = BASE_URL + "?search=" + titulo.replace(" ", "+");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonNode jsonNode = objectMapper.readTree(response.body());
                JsonNode resultados = jsonNode.get("results");

                return objectMapper.readValue(resultados.toString(), new TypeReference<List<LivroDTO>>() {});
            } else {
                System.err.println("Erro ao buscar livro. Código de status: " + response.statusCode());
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.err.println("Erro ao realizar requisição: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

