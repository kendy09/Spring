package br.org.generation.blog.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O campo deve ter nome ")
	@Size(min = 3, max = 60)
	private String nome;
	
	@NotNull(message ="Deve inserir o email")
	@Size(min =3, max = 100)
	@Email
	private String usuario;
	
	@Column(name = "data_nascimento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
	@NotNull(message = "O campo deve ter senha")
	@Size(min = 5, message = "A senha deve ter pelo menos 5 carecteres")
	private String senha;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	public List<Postagem> postagem; 
	
	
	
	public Usuario(String nome, @NotNull(message = "O campo deve ter nome ") @Size(min = 3, max = 60) String usuario,
			LocalDate dataNascimento,
			@NotNull(message = "O campo deve ter senha") @Size(min = 5, message = "A senha deve ter pelo menos 5 carecteres") String senha) {
		this.nome = nome;
		this.usuario = usuario;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
	}
	
	public Usuario () {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
	
}
