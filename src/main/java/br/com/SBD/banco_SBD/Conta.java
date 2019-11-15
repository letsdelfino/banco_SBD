package br.com.SBD.banco_SBD;

import java.math.BigDecimal;

public class Conta {
	private Integer id;
	private BigDecimal saldo;

	public Conta(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Conta() {
		this(new BigDecimal(0));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		strRetorno.append("\nSaldo: "+getSaldo().doubleValue());
		strRetorno.append("\n-------- ");

		return strRetorno.toString();
	}

}
