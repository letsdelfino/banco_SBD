import br.com.SBD.banco_SBD.*;
import java.sql.*;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		ContaDAOImplementacao contaDAO;
		try {
			contaDAO = new ContaDAOImplementacao();
			Conta conta = new Conta(1, 123l);
			contaDAO.set(conta);
			Conta conta2 = contaDAO.get(1);
			if(conta2 != null)
				System.out.println(conta2);
		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
}
