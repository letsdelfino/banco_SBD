package br.com.SBD.banco_SBD;

import java.math.BigDecimal;

public class Conta {

	private Integer id;
	private BigDecimal saldo;

	public Conta(Integer id, BigDecimal saldo) {
		this.id = id;
		this.saldo = saldo;
	}

	public Integer getId() {
		return id;
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
		strRetorno.append("\nId: " + getId());
		strRetorno.append("\nSaldo: " + getSaldo().doubleValue());
		strRetorno.append("\n-------- ");

		return strRetorno.toString();
	}

}
