package br.com.SBD.banco_SBD;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContaDAOImplementacao implements ContaDAO {

	/*public Conta consultar(String nome) {
		PreparedStatement ps = null;
		ResultSet rs;
		String url;
		Connection conexaoBanco = null;
		Conta conta;
		try {
			url = "jdbc:postgresql://postgres.crkhg7zmi4g5.sa-east-1.rds.amazonaws.com/postgres?user=postgres&password=postgres";
			conexaoBanco = DriverManager.getConnection(url);
			ps = conexaoBanco.prepareStatement("select id, nome, saldo from contas where nome=?");
			ps.setString(1, nome);
			rs = ps.executeQuery();

			if (rs.next()) {
				conta = new Conta();

				conta.setId(rs.getInt("id"));
				conta.setNome(rs.getString("nome"));
				conta.setSaldo(rs.getBigDecimal("saldo"));

				return conta;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (conexaoBanco != null) {
				try {
					conexaoBanco.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}*/

	public boolean inserir(Conta conta) {
		PreparedStatement ps = null;
		int rs;
		String url;
		Connection conexaoBanco = null;
		try {
			url = "jdbc:postgresql://postgres.crkhg7zmi4g5.sa-east-1.rds.amazonaws.com/postgres?user=postgres&password=postgres";
			conexaoBanco = DriverManager.getConnection(url);
			ps = conexaoBanco.prepareStatement("insert into contas (nome, saldo) values (?,?)");
			ps.setInt(1, conta.getId());
			ps.setString(2, conta.getNome());
			ps.setBigDecimal(3, conta.getSaldo());
			rs = ps.executeUpdate();

			if (rs > 0) {

				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (conexaoBanco != null) {
				try {
					conexaoBanco.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/*public Conta consultar(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs;
		String url;
		Connection conexaoBanco = null;
		Conta conta;
		try {
			url = "jdbc:postgresql://postgres.crkhg7zmi4g5.sa-east-1.rds.amazonaws.com/postgres?user=postgres&password=postgres";
			conexaoBanco = DriverManager.getConnection(url);
			ps = conexaoBanco.prepareStatement("select id, nome, saldo from contas where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				conta = new Conta();

				conta.setId(rs.getInt("id"));
				conta.setNome(rs.getString("nome"));
				conta.setSaldo(rs.getBigDecimal("saldo"));

				return conta;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (conexaoBanco != null) {
				try {
					conexaoBanco.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}*/
	
	public BigDecimal consultarSaldo(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs;
		String url;
		Connection conexaoBanco = null;
		Conta conta;
		try {
			url = "jdbc:postgresql://postgres.crkhg7zmi4g5.sa-east-1.rds.amazonaws.com/postgres?user=postgres&password=postgres";
			conexaoBanco = DriverManager.getConnection(url);
			ps = conexaoBanco.prepareStatement("select saldo from contas where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				conta = new Conta();

				conta.setSaldo(rs.getBigDecimal("saldo"));
				
				BigDecimal contaSaldo = conta.getSaldo();

				return contaSaldo;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (conexaoBanco != null) {
				try {
					conexaoBanco.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public List<Evento> listar(Integer id) {
		PreparedStatement ps = null;
		String url;
		Connection conexaoBanco = null;
		try{
			url = "jdbc:postgresql://postgres.crkhg7zmi4g5.sa-east-1.rds.amazonaws.com/postgres?user=postgres&password=postgres";
			conexaoBanco = DriverManager.getConnection(url);
			ps = conexaoBanco.prepareStatement("select data_evento, id_evento, tipo_evento, valor, id_origem_evento, id_destino_evento from eventos");
			try (ResultSet rs = ps.executeQuery()) {
				List<Evento> evento = new ArrayList<>();
				while (rs.next()) {
					Evento e = new Evento();
					e.setDataEvento(rs.getDate("data_evento"));
					e.setTipoOperacao(rs.getString("tipo_evento"));
					e.setValor(rs.getBigDecimal("valor"));
					
					e.setIdDestinoEvento(rs.getInt("id_destino_evento"));
					
					evento.add(e);
				}
				return evento;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}