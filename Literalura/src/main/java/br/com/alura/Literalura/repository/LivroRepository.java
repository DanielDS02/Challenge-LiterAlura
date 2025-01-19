package br.com.alura.Literalura.repository;

import br.com.alura.Literalura.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Query derivada para contar livros por idioma
    @Query("SELECT COUNT(l) FROM Livro l WHERE l.idioma = :idioma")
    long contarLivrosPorIdioma(@Param("idioma") String idioma);
}


