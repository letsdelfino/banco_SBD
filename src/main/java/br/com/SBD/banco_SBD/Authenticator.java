package br.com.SBD.banco_SBD;

import java.sql.*;

public interface Authenticator {
	public Cliente registerClient(String name, String email, String login,
	                              String password) throws SQLException;
	public boolean validateLogin(String login, String password)
		throws SQLException;
}
