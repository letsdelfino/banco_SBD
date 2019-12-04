import br.com.SBD.banco_SBD.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	static String SERVER_HOST = "expert.chickenkiller.com";
	static int SERVER_PORT = 1100;
	static Bank bank;
	static Scanner input;

	static void showEvents(Conta account) throws RemoteException, SQLException
	{
		System.out.printf("%13s%40s%8s%5s%5s\n",
		                  "Action", "Date", "Amount", "Source", "Destination");
		for(Event event: bank.getEvents(account))
		{
			System.out.printf("%13s", event.getAction());
			System.out.printf("%40s", event.getDate());
			System.out.printf("%8s", event.getAmount());
			System.out.printf("%5s", event.getSource().getId());
			if(event.getAction() == OperationType.TRANSFERENCE)
				System.out.printf("%5s", event.getDestination().getId());
			System.out.println("");
		}
	}

	static void printBalance(Long balance)
	{
		System.out.printf("Saldo atual: R$ %d,%02d\n",
		                  balance/100, balance%100);
	}

	//returns if should continue in the menu
	static boolean showAccountOptions(Bank bank, Conta account)
		throws RemoteException, SQLException
	{
		System.out.print("Número da Conta: " + account.getId() + "\n"
		                 + "Selecione uma das opções:\n"
		                 + "\t0 - Voltar\n"
		                 + "\t1 - Depósito\n"
		                 + "\t2 - Saque\n"
		                 + "\t3 - Transferência\n"
		                 + "\t4 - Saldo\n"
		                 + "\t5 - Extrato\n"
		                 + "Escolha: ");
		Integer choice;
		try {
			choice = input.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Por favor, digite um número.");
			input.skip(".*");
			return true;
		}
		Long amount;
		switch(choice) {
		case 0:
			return false;
		case 1:
			System.out.print("Digite o valor a ser depositado: ");
			try {
				amount = input.nextLong();
			} catch(InputMismatchException e) {
				System.out.println("Por favor, digite um número.");
				input.skip(".*");
				return true;
			}
			amount = bank.deposit(account, amount);
			printBalance(amount);
			break;
		case 2:
			System.out.print("Digite o valor a ser sacado: ");
			try {
				amount = input.nextLong();
			} catch(InputMismatchException e) {
				System.out.println("Por favor, digite um número.");
				input.skip(".*");
				return true;
			}
			amount = bank.withdraw(account, amount);
			printBalance(amount);
			break;
		case 3:
			System.out.print("Conta de destino: ");
			Integer accountNumber;
			try {
				accountNumber = input.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("Por favor, digite um número.");
				input.skip(".*");
				return true;
			}
			Conta destinationAccount = bank.getAccount(accountNumber);
			if(destinationAccount == null)
			{
				System.out.println("Conta não encontrada.");
				return true;
			}
			System.out.print("Valor: ");
			try {
				amount = input.nextLong();
			} catch(InputMismatchException e) {
				System.out.println("Por favor, digite um número.");
				input.skip(".*");
				return true;
			}
			bank.transfer(account, destinationAccount, amount);
			break;
		case 4:
			printBalance(account.getSaldo());
			break;
		case 5:
			showEvents(account);
			break;
		default:
			System.out.println("Esta não é uma opção válida!");
		}
		return true;
	}

	static void showRegisterForm(Bank bank) throws RemoteException, SQLException
	{
		System.out.print("Nome: ");
		String name = input.next();
		System.out.print("E-mail: ");
		String email = input.next();
		System.out.print("Login: ");
		String login = input.next();
		System.out.print("Senha: ");
		String senha = input.next();
		if(bank.getClient(login) == null)
		{
			Cliente client = bank.registerClient(name, email, login, senha);
			System.out.println("Conta criada com sucesso. Número: "
			                   + client.getConta().getId());
		}
		else
			System.out.println("Este login já está em uso. "
			                   + "Por favor, escolha outro.");
	}

	static void showLoginMenu(Bank bank) throws RemoteException, SQLException
	{
		System.out.print("Login: ");
		String login = input.next();
		System.out.print("Senha: ");
		String senha = input.next();
		if(bank.validateLogin(login, senha)) {
			Conta conta;
			do
			{
				conta = bank.getClient(login).getConta();
			} while(showAccountOptions(bank, conta));
		}
		else
			System.out.println("Login inválido");
	}

	public static void main(String[] args)
		throws RemoteException, SQLException, NotBoundException {
		try {
			Registry registry =
				LocateRegistry.getRegistry(SERVER_HOST, SERVER_PORT);
			bank = (Bank)registry.lookup("Bank");
		} catch(RemoteException e) {
			e.printStackTrace();
			return;
		}
		input = new Scanner(System.in);
		while(true) {
			System.out.print("Selecione uma das opções:\n\t0 - Sair\n"
			                 + "\t1 - Login\n\t2 - Cadastro\nEscolha: ");
			Integer choice;
			try {
				choice = input.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("Por favor, digite um número.");
				input.skip(".*");
				continue;
			}
			switch(choice) {
			case 0:
				return;
			case 1:
				showLoginMenu(bank);
				break;
			case 2:
				showRegisterForm(bank);
				break;
			default:
				System.out.println("Esta não é uma opção válida!");
			}
		}
	}
}
