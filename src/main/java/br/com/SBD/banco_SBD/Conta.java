package br.com.SBD.banco_SBD;

public class Conta {

	private Integer id;
	private Long saldo;

	public Conta(Integer id, Long saldo) {
		this.id = id;
		this.saldo = saldo;
	}

	public Integer getId() {
		return id;
	}

	public Long getSaldo() {
		return saldo;
	}

	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}

	public String toString() {
		StringBuilder strRetorno = new StringBuilder();
		strRetorno.append("-------- ");
		strRetorno.append("\nConta: ");
		strRetorno.append("\nId: " + getId());
		strRetorno.append("\nSaldo: " + getSaldo());
		strRetorno.append("\n-------- ");

		return strRetorno.toString();
	}

}
