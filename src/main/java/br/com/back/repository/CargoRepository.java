package br.com.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.back.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
