package br.com.vital.starwars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vital.starwars.dto.request.PlanetRequest;
import br.com.vital.starwars.dto.response.ListAllPlanetsResponse;
import br.com.vital.starwars.dto.response.PlanetResponse;
import br.com.vital.starwars.service.PlanetService;
import io.swagger.annotations.Api;

@Api(value = "API de cadastro de planetas")
@RestController
@RequestMapping("planet")
public class PlanetController {
	
	@Autowired
	private PlanetService planetService;
	
	@PostMapping(value = "/v1/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody PlanetRequest request) {
		planetService.save(request);
	}
	
	@ResponseBody
	@GetMapping(value = "v1/listAllSaved", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PlanetResponse>> listAllSaved() {
		return new ResponseEntity<List<PlanetResponse>>(planetService.listAllSaved(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "v1/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void remove(@RequestBody PlanetRequest request) {
		planetService.delete(request);
	}
	
	@ResponseBody
	@GetMapping(value = "v1/findByName")
	public ResponseEntity<List<PlanetResponse>> findByName(@RequestParam String name) {
		return new ResponseEntity<List<PlanetResponse>>(planetService.findByName(name), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "v1/findById")
	public ResponseEntity<PlanetResponse> findById(@RequestParam String id) {
		return new ResponseEntity<PlanetResponse>(planetService.findByID(id), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "v1/listAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ListAllPlanetsResponse>> listAll() {
		return new ResponseEntity<List<ListAllPlanetsResponse>>(planetService.listAll(), HttpStatus.OK);
	}
	
	

}
