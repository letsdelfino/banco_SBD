package br.com.SBD.banco_SBD;

import java.math.BigDecimal;
import java.util.List;

public interface ContaDAO {
//	public Conta consultar(String nome);
//	public Conta consultar(Integer id);
	public boolean inserir (Conta conta);
	public BigDecimal consultarSaldo(Integer id);
}
