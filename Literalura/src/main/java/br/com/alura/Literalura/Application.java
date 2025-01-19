package br.com.alura.Literalura;


import br.com.alura.Literalura.models.Autor;
import br.com.alura.Literalura.models.Livro;
import br.com.alura.Literalura.service.AutorService;
import br.com.alura.Literalura.service.LivroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Application {

	private final LivroService livroService;
	private final AutorService autorService;

	public Application(LivroService livroService, AutorService autorService) {
		this.livroService = livroService;
		this.autorService = autorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			int opcao;

			do {
				System.out.println("\n=== Catálogo de Livros ===");
				System.out.println("1. Buscar livro por título na API e salvar no banco");
				System.out.println("2. Listar todos os livros salvos");
				System.out.println("3. Exibir quantidade de livros em um idioma específico");
				System.out.println("4. Listar todos os autores salvos");
				System.out.println("5. Listar autores vivos em um ano específico");
				System.out.println("6. Sair");
				System.out.print("Escolha uma opção: ");
				opcao = scanner.nextInt();
				scanner.nextLine(); // Consumir o caractere de nova linha

				switch (opcao) {
					case 1 -> listarTodosOsLivros();
					case 2 -> exibirQuantidadeLivrosPorIdioma(scanner);
					case 3 -> listarTodosOsAutores();
					case 4 -> listarAutoresVivos(scanner);
					case 5 -> System.out.println("Saindo do programa...");
					default -> System.out.println("Opção inválida. Tente novamente.");
				}
			} while (opcao != 5);
		};
	}

	private void listarTodosOsLivros() {
		List<Livro> livros = livroService.listarLivros();
		if (livros.isEmpty()) {
			System.out.println("Nenhum livro encontrado no banco.");
		} else {
			System.out.println("Livros salvos no banco:");
			livros.forEach(System.out::println);
		}
	}

	private void exibirQuantidadeLivrosPorIdioma(Scanner scanner) {
		System.out.print("Digite o idioma (ex: 'en' para inglês): ");
		String idioma = scanner.nextLine();
		long quantidade = livroService.contarLivrosPorIdioma(idioma);
		System.out.println("Quantidade de livros em " + idioma + ": " + quantidade);
	}

	private void listarTodosOsAutores() {
		List<Autor> autores = autorService.listarAutores();
		if (autores.isEmpty()) {
			System.out.println("Nenhum autor encontrado no banco.");
		} else {
			System.out.println("Autores salvos no banco:");
			autores.forEach(System.out::println);
		}
	}

	private void listarAutoresVivos(Scanner scanner) {
		System.out.print("Digite o ano para listar autores vivos: ");
		int ano = scanner.nextInt();
		scanner.nextLine(); // Consumir o caractere de nova linha

		List<Autor> autoresVivos = autorService.listarAutoresVivosEmAno(ano);
		if (autoresVivos.isEmpty()) {
			System.out.println("Nenhum autor encontrado vivo no ano " + ano + ".");
		} else {
			System.out.println("Autores vivos em " + ano + ":");
			autoresVivos.forEach(System.out::println);
		}
	}
}
