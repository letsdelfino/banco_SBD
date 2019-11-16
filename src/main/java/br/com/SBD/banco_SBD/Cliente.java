package br.com.SBD.banco_SBD;

public class Cliente {

	private Integer id;
	private String nome;
	private String email;
	private String login;
	private String senha;

	public Cliente(Integer id, String nome, String email,
	               String login, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String toString() {
		StringBuilder strRetorno = new StringBuilder();
		strRetorno.append("----------------");
		strRetorno.append("\nNúmero da Conta: " + getId());
		strRetorno.append("\nNome: " + getNome());
		strRetorno.append("\nEmail: " + getEmail());
		strRetorno.append("\nLogin: " + getLogin());
		return strRetorno.toString();
	}
}
