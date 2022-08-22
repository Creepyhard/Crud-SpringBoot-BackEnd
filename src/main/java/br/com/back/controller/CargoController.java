package br.com.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.back.exception.ResourceNotFoundException;
import br.com.back.model.Cargo;
import br.com.back.repository.CargoRepository;
import br.com.back.service.CargoService;

@RestController
@RequestMapping(value = "/cargos")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@GetMapping
	public List<Cargo> listarCargo() {
		return cargoRepository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Cargo> buscarId(@PathVariable long id) {
		return cargoService.buscarIdService(id);
	}
	
	@PostMapping
	public Cargo adicionarCargo(@RequestBody Cargo cargo) {
		return cargoService.adicionarCargoService(cargo);
	}
	
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<Object> deleteCargo(@PathVariable long id) {
		return cargoService.deleteCargoService(id);
	}
	
}
