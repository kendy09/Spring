package br.org.generation.mentalidades;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mentalidades")
public class Mentalidades {
	@GetMapping
	public String mentalidades(){
	
		return "orientação ao futuro, persistencia";
	
	}
}
