package br.com.SBD.banco_SBD;

import java.util.ArrayList;
import java.sql.SQLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BankImpl extends UnicastRemoteObject implements Bank {
	private static Bank instance;
	private ContaDAO contaDAO;
	private ClienteDAO clienteDAO;
	private EventDAO eventDAO;

	private BankImpl()
		throws RemoteException, SQLException, ClassNotFoundException
	{
		super(1099);
		contaDAO = ContaDAOImplementacao.getInstance();
		clienteDAO = ClienteDAOImplementacao.getInstance();
		eventDAO = EventDAOImpl.getInstance();
	}

	public static Bank getInstance()
		throws RemoteException, SQLException, ClassNotFoundException
	{
		if(instance == null)
			instance = new BankImpl();
		return instance;
	}

	public Cliente registerClient(String name, String email, String login,
	                              String password)
		throws RemoteException, SQLException
	{
		Cliente cliente =
			new Cliente(clienteDAO.getNewId(), name, email, login,
			            password, new Conta(contaDAO.getNewId(), 0l));
		Event event = new Event(eventDAO.getNewId(), OperationType.ACCOUNT_OPEN,
		                        0l, cliente.getConta(), cliente.getConta());
		cliente.getConta().setSaldo(10000l);
		eventDAO.set(event);
		event = new Event(eventDAO.getNewId(), OperationType.DEPOSIT,
		                  10000l, cliente.getConta(), cliente.getConta());
		eventDAO.set(event);
		clienteDAO.set(cliente);
		return cliente;
	}

	public boolean validateLogin(String login, String password)
		throws RemoteException, SQLException
	{
		Cliente cliente = clienteDAO.get(login);
		if(cliente == null)
			return false;
		return cliente.getSenha().equals(password);
	}

	public Long deposit(Conta conta, Long valor)
		throws RemoteException, SQLException {
		conta = contaDAO.get(conta.getId());
		conta.setSaldo(conta.getSaldo() + valor);
		contaDAO.set(conta);
		Event event = new Event(eventDAO.getNewId(), OperationType.DEPOSIT,
		                        valor, conta, conta);
		eventDAO.set(event);
		return conta.getSaldo();
	}

	public Long withdraw(Conta conta, Long valor)
		throws RemoteException, SQLException {
		conta = contaDAO.get(conta.getId());
		if(conta.getSaldo() >= valor) {
			conta.setSaldo(conta.getSaldo() - valor);
			contaDAO.set(conta);
			Event event = new Event(eventDAO.getNewId(), OperationType.WITHDRAW,
			                        valor, conta, conta);
			eventDAO.set(event);
		}
		return conta.getSaldo();
	}

	public void transfer(Conta origem, Conta destino, Long valor)
		throws RemoteException, SQLException {
		origem = contaDAO.get(origem.getId());
		destino = contaDAO.get(destino.getId());
		if(origem.getSaldo() >= valor) {
			origem.setSaldo(origem.getSaldo() - valor);
			destino.setSaldo(origem.getSaldo() + valor);
			contaDAO.set(origem);
			contaDAO.set(destino);
			Event event = new Event(eventDAO.getNewId(),
			                        OperationType.TRANSFERENCE, valor,
			                        origem, destino);
			eventDAO.set(event);
		}
	}

	public Conta getAccount(Integer id) throws RemoteException, SQLException
	{
		return contaDAO.get(id);
	}

	public Cliente getClient(String login) throws RemoteException, SQLException
	{
		return clienteDAO.get(login);
	}

	public ArrayList<Event> getEvents(Conta conta)
		throws RemoteException, SQLException
	{
		return eventDAO.get(conta);
	}
}
