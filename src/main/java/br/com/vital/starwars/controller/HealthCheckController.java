package br.com.vital.starwars.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("healthcheck")
public class HealthCheckController {
	
	@GetMapping("/isAlive")
	public String isAlive() {
		return "Star Wars Api is Alive";
	}

}
