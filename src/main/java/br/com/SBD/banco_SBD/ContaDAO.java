package br.com.SBD.banco_SBD;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ContaDAO {
	public void set(Conta conta) throws SQLException;
	//can return null
	public Conta get(Integer id) throws SQLException;
	public ArrayList<Conta> getAll() throws SQLException;
	public void delete(Integer id) throws SQLException;
	public Integer getNewId() throws SQLException;
}
