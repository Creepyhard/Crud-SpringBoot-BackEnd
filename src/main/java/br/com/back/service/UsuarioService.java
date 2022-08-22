package br.com.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.back.exception.ResourceNotFoundException;
import br.com.back.exception.UserNotFoundException;
import br.com.back.model.Usuario;
import br.com.back.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public ResponseEntity<Usuario> buscarIdService(@PathVariable long id) {
		return usuarioRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElseThrow(() -> new ResourceNotFoundException("Não existe este id de usuario :" + id));
	}
	
	public ResponseEntity<Object> deleteUsuarioService(@PathVariable long id) {
		return usuarioRepository.findById(id).map(record -> {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Não existe este id de usuario :" + id));
	}
	
	public ResponseEntity<Usuario> atualizarUsuarioService(Long id, Usuario usuarioDetalhes){
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe este id de usuario :" + id + " Confirme os dados inseridos!!"));
		
		usuario.setNome(usuarioDetalhes.getNome());
		usuario.setCpf(usuarioDetalhes.getCpf());
		usuario.setSenha(usuarioDetalhes.getSenha());
		usuario.setCargo(usuarioDetalhes.getCargo());
		usuario.setDataNascimento(usuarioDetalhes.getDataNascimento());
		
		Usuario atualizausuario = usuarioRepository.save(usuario);
		return ResponseEntity.ok(atualizausuario);
	}
	
	public Usuario loginUsuarioService(Usuario usuario) throws UserNotFoundException {
		String cpfT = usuario.getCpf();
		String senhaT = usuario.getSenha();
		Usuario usuarioObjeto = null;
		if(cpfT != null && senhaT != null) {
			usuarioObjeto = usuarioRepository.acharIdESenha(cpfT, senhaT);
		}
		if(usuarioObjeto == null) {
			throw new UserNotFoundException();
		}
		return usuarioObjeto;
	}
}
