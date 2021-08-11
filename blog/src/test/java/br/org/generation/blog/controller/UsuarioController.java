package br.org.generation.blog.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import br.org.generation.blog.model.Usuario;
import br.org.generation.blog.repository.UsuarioRepository;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS )
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioController {
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private Usuario usuario;
	private Usuario usuarioAdmin;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void star() {
		
		LocalDate dataAdmin = LocalDate.parse("1994-08-13",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		usuarioAdmin = new Usuario("Roberto","percloreto@gmail.com",dataAdmin,"admin1234");
		
		if(usuarioRepository.findByUsuario(usuarioAdmin.getUsuario()).isPresent()) {
			HttpEntity<Usuario>request = new HttpEntity<Usuario>(usuarioAdmin);
			testRestTemplate.exchange("/usuarios/cadastrar",HttpMethod.POST, request, Usuario.class);
		}
		
		LocalDate dataPut = LocalDate.parse("1991-08-12", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Usuario usuarioUpdate = new Usuario("Ana","Gisele@hotmail.com",dataPut,"jiasiosjdas");
		
		LocalDate dataUsuario = LocalDate.parse("1992-03-13",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Usuario usuario = new Usuario("Rogerio","Hirata@hotmail.com",dataUsuario,"diodsiffas");
	}
	
}
