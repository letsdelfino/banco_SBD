package br.com.SBD.banco_SBD;

import java.sql.*;

public class AuthenticatorImpl implements Authenticator {
	private static AuthenticatorImpl instance;
	private ContaDAO contaDAO;
	private ClienteDAO clienteDAO;

	private AuthenticatorImpl() throws SQLException, ClassNotFoundException
	{
		contaDAO = ContaDAOImplementacao.getInstance();
		clienteDAO = ClienteDAOImplementacao.getInstance();
	}

	public static AuthenticatorImpl getInstance()
		throws SQLException, ClassNotFoundException
	{
		if(instance == null)
			instance = new AuthenticatorImpl();
		return instance;
	}

	public Cliente registerClient(String name, String email, String login,
	                              String password) throws SQLException
	{
		Cliente cliente =
			new Cliente(clienteDAO.getNewId(), name, email, login,
			            password, new Conta(contaDAO.getNewId(), 0l));
		clienteDAO.set(cliente);
		return cliente;
	}

	public boolean validateLogin(String login, String password)
		throws SQLException
	{
		Cliente cliente = clienteDAO.get(login);
		if(cliente == null)
			return false;
		return cliente.getSenha().equals(password);
	}

}
