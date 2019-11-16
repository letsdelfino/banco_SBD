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

	@Override
	public String toString() {
		return "Conta{"
			+ "id='" + id + "'"
			+ "saldo='" + saldo + "'"
			+ "}";
	}

}
