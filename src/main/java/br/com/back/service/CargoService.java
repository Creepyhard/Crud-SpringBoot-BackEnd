package br.com.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.back.exception.ResourceNotFoundException;
import br.com.back.model.Cargo;
import br.com.back.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;
	
	public ResponseEntity<Cargo> buscarIdService(long id) {
		return cargoRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElseThrow(() -> new ResourceNotFoundException("Não existe este id de cargo :" + id));
	}

	public Cargo adicionarCargoService(Cargo cargo) {
		return cargoRepository.save(cargo);
	}
	
	public ResponseEntity<Object> deleteCargoService(long id) {
		return cargoRepository.findById(id).map(record -> {
			cargoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Não existe este id de cargo :" + id));
	}
}
