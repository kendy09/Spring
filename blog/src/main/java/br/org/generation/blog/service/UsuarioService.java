package br.org.generation.blog.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.blog.model.Usuario;
import br.org.generation.blog.model.UsuarioLogin;
import br.org.generation.blog.repository.UsuarioRepository;

@Service
public class UsuarioService  {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario>cadastrarUsuario(Usuario usuario){
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario ja existe");
		}
		
		int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
		
		if(idade <18) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario menor de idade");
		}
		
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		String senhaEnconder = enconder.encode(usuario.getSenha());
		usuario.setSenha(senhaEnconder);
		
		return Optional.of(usuarioRepository.save(usuario));
	}
	
	
	public Optional<Usuario>atualizarUsuario(Usuario usuario){
		
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			
			int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
			if(idade<18) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario menor de idade");
			}
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		String senhaEnconder = enconder.encode(usuario.getSenha());
		usuario.setSenha(senhaEnconder);
		return Optional.of(usuarioRepository.save(usuario));
		
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
		}
	}
	
	public Optional<UsuarioLogin>logarUsuario(Optional<UsuarioLogin> usuarioLogin){
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
		
		if (usuario.isPresent()) {
			
			if (enconder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())){
				String auth = usuarioLogin.get().getUsuario()+":"+usuarioLogin.get().getSenha();
				byte[] encondedauth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHearder = "Basic" + new String(encondedauth);
				
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setToken(authHearder);
				
				return usuarioLogin;
			}
				
	}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha ou usuario invalidos");
}
	
}
