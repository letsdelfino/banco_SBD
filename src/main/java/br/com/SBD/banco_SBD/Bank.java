package br.com.SBD.banco_SBD;

import java.util.ArrayList;
import java.sql.SQLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank extends Remote {
	public Long deposit(Conta conta, Long valor)
		throws RemoteException, SQLException;
	public Long withdraw(Conta conta, Long valor)
		throws RemoteException, SQLException;
	public void transfer(Conta origem, Conta destino, Long valor)
		throws RemoteException, SQLException;
	public Cliente registerClient(String name, String email, String login,
	                              String password)
		throws RemoteException, SQLException;
	public boolean validateLogin(String login, String password)
		throws RemoteException, SQLException;
	//can return null
	public Conta getAccount(Integer id) throws RemoteException, SQLException;
	//can return null
	public Cliente getClient(String login) throws RemoteException, SQLException;
	public ArrayList<Event> getEvents(Conta conta)
		throws RemoteException, SQLException;
}
