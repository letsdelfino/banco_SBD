package br.com.SBD.banco_SBD;

public class Cliente {
	private Integer id_cadastro;
	private String nome;
	private String email;
	private String login;
	private String senha;

	public Integer getId_cadastro() {
		return id_cadastro;
	}
	public void setId_cadastro(Integer id_cadastro) {
		this.id_cadastro = id_cadastro;
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
		strRetorno.append("\nNúmero da Conta: " + getId_cadastro());
		strRetorno.append("\nCliente: " + getNome());
		strRetorno.append("\nEmail: " + getEmail());
		strRetorno.append("\nLogin: " + getLogin());

		return strRetorno.toString();
	}


}
