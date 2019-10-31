package br.com.SBD.banco_SBD;

import java.math.BigDecimal;
import java.util.List;

public interface ContaDAO {
	public Conta consultar(String nome);
	public boolean inserir (Conta conta);
	public List<Conta> listar(String nome);
	public BigDecimal consultarSaldo(Integer id);
}
