package br.com.SBD.banco_SBD;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClienteDAO {
	public void set(Cliente conta) throws SQLException;
	//can return null
	public Cliente get(Integer id) throws SQLException;
	//can return null
	public Cliente get(String login) throws SQLException;
	public ArrayList<Cliente> getAll() throws SQLException;
	public void delete(Integer id) throws SQLException;
	public Integer getNewId() throws SQLException;
}
