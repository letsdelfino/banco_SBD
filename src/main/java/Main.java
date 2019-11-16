import br.com.SBD.banco_SBD.*;
import java.sql.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ContaDAO contaDAO;
		ClienteDAO clienteDAO;
		try {
			contaDAO = ContaDAOImplementacao.getInstance();
			clienteDAO = ClienteDAOImplementacao.getInstance();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return;
		}

		Integer accountID;
		Integer clientID;
		try {
			accountID = contaDAO.getNewId();
			clientID = clienteDAO.getNewId();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		Cliente cliente =
			new Cliente(clientID, System.getenv("USER") + "",
			            System.getenv("USER") + "@mailserver.net",
			            System.getenv("USER") + "",
			            "password123", new Conta(accountID, 123l));
		try {
			System.out.println("All clients:");
			clienteDAO.set(cliente);
			for(Cliente c : clienteDAO.getAll())
				System.out.println(c);
			// clienteDAO.delete(clientID);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
