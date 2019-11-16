package br.com.SBD.banco_SBD;

import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.List;

public interface ContaDAO {
	public void set(Conta conta) throws SQLException;
	//can return null
	public Conta get(Integer id) throws SQLException;
}
