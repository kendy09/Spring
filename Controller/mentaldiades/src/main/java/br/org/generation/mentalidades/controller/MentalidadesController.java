package br.org.generation.mentalidades.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-mentalidades")
public class MentalidadesController {
	@GetMapping
	public String HelloMentalidades() {
		return "Persistencia e orientação ao futuro";
	}
}
