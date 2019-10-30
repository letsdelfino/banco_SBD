package br.com.SBD.banco_SBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContaDAOImplementacao implements ContaDAO {

	public Conta consultar(String nome) {
		PreparedStatement ps = null;
		ResultSet rs;
		String url;
		Connection conexaoBanco = null;
		Conta conta;
		try {
			url = "jdbc:postgresql://127.0.0.1/postgres?user=postgres&password=@Wonder777";
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
	}

	public boolean inserir(Conta conta) {
		PreparedStatement ps = null;
		int rs;
		String url;
		Connection conexaoBanco = null;
		try {
			url = "jdbc:postgresql://127.0.0.1/postgres?user=postgres&password=@Wonder777";
			conexaoBanco = DriverManager.getConnection(url);
			ps = conexaoBanco.prepareStatement("insert into contas (id, nome, saldo) values (?,?,?)");
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

	public Conta consultar(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs;
		String url;
		Connection conexaoBanco = null;
		Conta conta;
		try {
			url = "jdbc:postgresql://127.0.0.1/postgres?user=postgres&password=@Wonder777";
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

	}

	public boolean excluir(Conta conta) {
		PreparedStatement ps = null;
		String url;
		Connection conexaoBanco = null;
		try {
			url = "jdbc:postgresql://127.0.0.1/postgres?user=postgres&password=@Wonder777";
			conexaoBanco = DriverManager.getConnection(url);
			ps = conexaoBanco.prepareStatement("delete from contas where id=?");
			ps.setInt(1, conta.getId());

			if (ps.executeUpdate() > 0)
				return true;
			else
				return false;

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

	public List<Conta> listar(String nome) {
		PreparedStatement ps = null;
		String url;
		Connection conexaoBanco = null;
		try{
			url = "jdbc:postgresql://127.0.0.1/postgres?user=postgres&password=@Wonder777";
			conexaoBanco = DriverManager.getConnection(url);
			ps = conexaoBanco.prepareStatement("select id, nome, saldo from contas");
			try (ResultSet rs = ps.executeQuery()) {
				List<Conta> conta = new ArrayList<>();
				while (rs.next()) {
					Conta c = new Conta();
					c.setId(rs.getInt("id"));
					c.setNome(rs.getString("nome"));
					c.setSaldo(rs.getBigDecimal("saldo"));
					
					conta.add(c);
				}
				return conta;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}