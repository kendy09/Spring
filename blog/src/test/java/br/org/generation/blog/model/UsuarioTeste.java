package br.org.generation.blog.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioTeste {
	public Usuario usuario;
	public Usuario usuarioNulo;
	
	@Autowired
	private ValidatorFactory factory = javax.validation.Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	@BeforeEach
	public void star() {
		LocalDate data = LocalDate.parse("2000-07-05",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		usuario = new Usuario("joao da silva","joao@gmail.com",data,"12345677");
	}
	
	@Test
	@DisplayName("✔ validar atributos não nulos ")
	public void testValidarAtributos() {
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
		System.out.println(violacao.toString());
		assertTrue(violacao.isEmpty());
	}
	@Test
	public void testNaoValidarAtributos() {
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioNulo);
		System.out.println(violacao.toString());
		assertTrue(violacao.isEmpty());
	}
}