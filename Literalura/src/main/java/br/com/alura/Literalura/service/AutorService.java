package br.com.alura.Literalura.service;

import br.com.alura.Literalura.models.Autor;
import br.com.alura.Literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarAutoresVivosEmAno(int ano) {
        return autorRepository.listarAutoresVivosEmAno(ano);
    }


    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }
}
