package br.com.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.back.model.Usuario;
import br.com.back.repository.UsuarioRepository;
import br.com.back.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> listarUsuario() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Usuario> buscarId(@PathVariable long id) {
		return usuarioService.buscarIdService(id);
	}
	
	@PostMapping
	public Usuario adicionarUsuario(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<Object> deleteUsuario(@PathVariable long id) {
		return usuarioService.deleteUsuarioService(id);
	}
	
	@PutMapping({"/{id}"})
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetalhes){
		return usuarioService.atualizarUsuarioService(id, usuarioDetalhes);
	}
	
	@PostMapping("/login")
	private Usuario loginUsuario(@RequestBody Usuario usuario){
		return usuarioService.loginUsuarioService(usuario);
	}
}
