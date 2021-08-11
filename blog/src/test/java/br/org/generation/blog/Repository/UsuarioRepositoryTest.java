package br.org.generation.blog.Repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blog.model.Usuario;
import br.org.generation.blog.repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/*@BeforeAll
	void start() {
		LocalDate data  = LocalDate.parse("2003-06-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Usuario usuario = new Usuario(0L, "Mario andrade","marioand@hotmail.com","1234567s",data);

		if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			usuarioRepository.save(usuario);
		}
		usuario = new Usuario(0L, "Henrique da Silva","henriquinho@outlook.com","12355122",data);
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isEmpty()) {
			usuarioRepository.save(usuario);
		}
		usuario = new Usuario(0L, "Fabio da Silva","Fabiomendo@gmail.com","12312329013",data);
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {	
			usuarioRepository.save(usuario);
		}
	}*/
	@Test
	@DisplayName("💾 Retorna o nome")
	void findByNomeRetornarNome() {
		LocalDate data  = LocalDate.parse("2003-06-21", DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
		Usuario usuario = new Usuario("Joao da Silva","jaosil@outlook.com",data,"12312455");
		assertTrue(usuario.getNome().contains("Joao da Silva"));		
	}
	
	@Test
	@DisplayName("💾 Retorna 3 usuarios")
	void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() { 

		List<Usuario>usuario = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		for(Usuario e : usuario) {
			System.out.println(e);
		}
		assertEquals(3, usuario.size());
	}
	@AfterAll
	public void end() {
		
		System.out.println("Teste Finalizado!");
		
	}
}
