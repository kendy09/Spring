package br.org.generation.aprendizado.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aprendizado")
public class AprendizadoController {
	@GetMapping
	public String aprendendi() {
		return"Essa semana eu aprendi a fazer um CRUD"
				+ "e a fazer um controller para a minha API";
	}
}
