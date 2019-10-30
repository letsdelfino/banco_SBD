package br.com.SBD.banco_SBD;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		ContaDAOImplementacao contaDAO = new ContaDAOImplementacao();

		Conta contaJoaoConsulta = contaDAO.consultar("Diego");
		System.out.println(contaJoaoConsulta.toString());

		Conta contaId5 = contaDAO.consultar(3);
		contaDAO.excluir(contaId5);
		
		List<Conta> lista = contaDAO.listar("Diego");
		System.out.println(lista);

	}
}

