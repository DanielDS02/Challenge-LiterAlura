package br.com.alura.Literalura.service;

import br.com.alura.Literalura.dto.LivroDTO;
import br.com.alura.Literalura.models.Autor;
import br.com.alura.Literalura.models.Livro;
import br.com.alura.Literalura.repository.AutorRepository;
import br.com.alura.Literalura.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    // Método para listar todos os livros
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public long contarLivrosPorIdioma(String idioma) {
        return livroRepository.contarLivrosPorIdioma(idioma);
    }


    public Livro salvarLivro(LivroDTO livroDTO) {
        Autor autor = new Autor();
        autor.setNome(livroDTO.getAutores()[0].getName());
        autor.setAnoNascimento(livroDTO.getAutores()[0].getAnoNascimento());
        autor.setAnoFalecimento(livroDTO.getAutores()[0].getAnoFalecimento());

        Livro livro = new Livro();
        livro.setTitulo(livroDTO.getTitle());
        livro.setIdioma(livroDTO.getIdiomas()[0]);
        livro.setDownloads(livroDTO.getDownloads());
        livro.setAutor(autor);

        return livroRepository.save(livro);
    }


}

