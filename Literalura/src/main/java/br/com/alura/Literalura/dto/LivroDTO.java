package br.com.alura.Literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroDTO {
    private String title;

    @JsonAlias("languages")
    private String[] idiomas;

    @JsonAlias("download_count")
    private int downloads;

    @JsonAlias("authors")
    private AutorDTO[] autores;

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String[] idiomas) {
        this.idiomas = idiomas;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public AutorDTO[] getAutores() {
        return autores;
    }

    public void setAutores(AutorDTO[] autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "LivroDTO{" +
                "title='" + title + '\'' +
                ", idiomas='" + idiomas[0] + '\'' +
                ", downloads=" + downloads +
                ", autores=" + (autores.length > 0 ? autores[0] : null) +
                '}';
    }
}

