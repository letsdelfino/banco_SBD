package br.com.SBD.banco_SBD;

import java.sql.SQLException;

import java.util.ArrayList;

public interface EventDAO {
	public void set(Event event) throws SQLException;
	public ArrayList<Event> get(OperationType action) throws SQLException;
	public ArrayList<Event> get(Conta conta) throws SQLException;
	public ArrayList<Event> getAll() throws SQLException;
	public void delete(Integer id) throws SQLException;
	public Integer getNewId() throws SQLException;
}
