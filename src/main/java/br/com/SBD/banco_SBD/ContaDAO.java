package br.com.SBD.banco_SBD;

import java.sql.SQLException;

public interface ContaDAO {
	public void set(Conta conta) throws SQLException;
	//can return null
	public Conta get(Integer id) throws SQLException;
}
