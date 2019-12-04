import br.com.SBD.banco_SBD.*;

import java.util.ArrayList;
import java.sql.SQLException;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Server extends UnicastRemoteObject implements Bank {
	static int SERVER_PORT = 1100;
	static int WORKER_PORT = 1099;
	static String WORKER_HOSTS[] =
	{
		"127.0.0.1",
		"expert.chickenkiller.com",
	};
	static ArrayList<Bank> workers = new ArrayList<>();
	static int lastUsedWorker = 0;

	static Bank instance;

	private Server() throws RemoteException
	{
		super(SERVER_PORT);
	}

	public static void main(String args[]) {
		for(int i=0; i<WORKER_HOSTS.length; i++)
		{
			try {
				Registry registry =
					LocateRegistry.getRegistry(WORKER_HOSTS[i], WORKER_PORT);
				Bank worker = (Bank)registry.lookup("Bank");
				workers.add(worker);
				System.out.println("Connected to worker " + WORKER_HOSTS[i]);
			} catch(RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
		}
		try {
			Bank obj = Server.getInstance();
			Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
			registry.rebind("Bank", obj);
		} catch (RemoteException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.printf("Dispatching jobs to %d workers\n", workers.size());
		System.out.printf("Waiting for clients...\n");
	}

	static Bank getInstance()
		throws RemoteException, SQLException, ClassNotFoundException
	{
		if(instance == null)
			instance = new Server();
		return instance;
	}

	public Bank getWorker()
	{
		lastUsedWorker++;
		if(lastUsedWorker >= workers.size())
			lastUsedWorker = 0;
		return workers.get(lastUsedWorker);
	}

	public String getWorkerHost(Bank worker)
	{
		Pattern pattern = Pattern.compile(".*\\[endpoint:.(.*:\\d*)\\].*");
		Matcher matcher = pattern.matcher(worker.toString());
		if (matcher.find())
			return matcher.group(1);
		return "?";
	}

	public Long deposit(Conta conta, Long valor)
		throws RemoteException, SQLException
	{
		Bank worker = getWorker();
		System.out.println(getWorkerHost(worker) + " --> " + "deposit");
		return worker.deposit(conta, valor);
	}

	public Long withdraw(Conta conta, Long valor)
		throws RemoteException, SQLException
	{
		Bank worker = getWorker();
		System.out.println(getWorkerHost(worker) + " --> " + "withdraw");
		return worker.withdraw(conta, valor);
	}

	public void transfer(Conta origem, Conta destino, Long valor)
		throws RemoteException, SQLException
	{
		Bank worker = getWorker();
		System.out.println(getWorkerHost(worker) + " --> " + "transfer");
		worker.transfer(origem, destino, valor);
	}

	public Cliente registerClient(String name, String email, String login,
	                              String password)
		throws RemoteException, SQLException
	{
		Bank worker = getWorker();
		System.out.println(getWorkerHost(worker) + " --> " + "registerClient");
		return worker.registerClient(name, email, login, password);
	}

	public boolean validateLogin(String login, String password)
		throws RemoteException, SQLException
	{
		Bank worker = getWorker();
		System.out.println(getWorkerHost(worker) + " --> " + "validateLogin");
		return worker.validateLogin(login, password);
	}

	public Conta getAccount(Integer id) throws RemoteException, SQLException
	{
		Bank worker = getWorker();
		System.out.println(getWorkerHost(worker) + " --> " + "getAccount");
		return worker.getAccount(id);
	}

	public Cliente getClient(String login) throws RemoteException, SQLException
	{
		Bank worker = getWorker();
		System.out.println(getWorkerHost(worker) + " --> " + "getClient");
		return worker.getClient(login);
	}

	public ArrayList<Event> getEvents(Conta conta)
		throws RemoteException, SQLException
	{
		Bank worker = getWorker();
		System.out.println(getWorkerHost(worker) + " --> " + "getEvents");
		return worker.getEvents(conta);
	}
}
