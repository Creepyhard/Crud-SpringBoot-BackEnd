package br.com.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.back.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(nativeQuery = true, value = "select u.* from usuario u where u.cpf = :cpf and u.senha = :senha")
	public Usuario acharIdESenha(@Param("cpf") String cpfT, @Param("senha") String senhaT);

}
