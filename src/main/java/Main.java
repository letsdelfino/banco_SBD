import br.com.SBD.banco_SBD.*;
import java.sql.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Authenticator auth;
		ContaDAO contaDAO;
		ClienteDAO clienteDAO;
		Integer newClientID;
		try {
			contaDAO = ContaDAOImplementacao.getInstance();
			clienteDAO = ClienteDAOImplementacao.getInstance();
			auth = AuthenticatorImpl.getInstance();
			newClientID = clienteDAO.getNewId();
			Cliente cliente =
				auth.registerClient(System.getenv("USER") + "",
				                    System.getenv("USER") + "@mailserver.net",
				                    System.getenv("USER") + newClientID,
				                    "password123");
			System.out.println("New client id: " + cliente.getId());
			System.out.println("All clients:");
			clienteDAO.set(cliente);
			for(Cliente c : clienteDAO.getAll())
				System.out.println(c);
			// clienteDAO.delete(newClientID);
			System.out.println("Client xp2: " + clienteDAO.get("xp2"));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
