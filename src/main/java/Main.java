import br.com.SBD.banco_SBD.*;
import java.math.BigDecimal;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		ContaDAOImplementacao contaDAO = new ContaDAOImplementacao();

		//Conta contaJoaoConsulta = contaDAO.consultar("Diego");
		//System.out.println(contaJoaoConsulta.toString());

		//BigDecimal contaId = contaDAO.consultarSaldo(2);
		//System.out.print(contaId);
		//contaDAO.excluir(contaId5);

		List<Evento> lista = contaDAO.listar(1);
		System.out.println(lista);

	}
}
