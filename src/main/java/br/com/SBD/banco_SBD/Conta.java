package br.com.SBD.banco_SBD;

import java.math.BigDecimal;

public class Conta {
	private Integer id;
	private String nome;
	private BigDecimal saldo;

	public Conta(String nome, BigDecimal saldo) {
		this.nome = nome;
		this.saldo = saldo;
	}

	public Conta() {
		this.saldo = new BigDecimal(0);
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

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String toString() {
		StringBuilder strRetorno = new StringBuilder();
		strRetorno.append("-------- ");
		strRetorno.append("\nConta: ");
		strRetorno.append("\nId: "+getId());
		strRetorno.append("\nNome:"+getNome());
		strRetorno.append("\nSaldo: "+getSaldo().doubleValue());
		strRetorno.append("\n-------- ");
		
		return strRetorno.toString(); 
	}

}
