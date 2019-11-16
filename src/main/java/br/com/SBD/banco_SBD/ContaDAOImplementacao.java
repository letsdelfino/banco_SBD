package br.com.SBD.banco_SBD;

import java.sql.*;

public class ContaDAOImplementacao implements ContaDAO {
	private final String tableName = "accounts";

	private Database database;
	private PreparedStatement getStatement;
	private PreparedStatement setStatement;

	private final String sqlCreateTable =
		"CREATE TABLE IF NOT EXISTS " + tableName + " ("
		+ " `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT, "
		+ " `balance` BIGINT NOT NULL, "
		+ " PRIMARY KEY (`ID`)"
		+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

	private final String sqlGet = "SELECT * FROM " + tableName + " WHERE ID=?";
	private final String sqlSet =
		"IF EXISTS (SELECT * FROM " + tableName + " WHERE ID=?) THEN" +
		"	UPDATE " + tableName + " SET balance=? WHERE ID=?;" +
		"ELSE" +
		"	INSERT INTO " + tableName + " SET balance=?;" +
		"END IF";

	public ContaDAOImplementacao() throws SQLException, ClassNotFoundException
	{
		database = Database.getInstance();
		createTableIfNotExists();
		prepareStatements();
	}

	private void createTableIfNotExists() throws SQLException
	{
		Statement statement = database.getConnection().createStatement();
		statement.executeUpdate(sqlCreateTable);
	}

	private void prepareStatements() throws SQLException
	{
		getStatement = database.getConnection().prepareStatement(sqlGet);
		setStatement = database.getConnection().prepareStatement(sqlSet);
	}

	public void set(Conta conta) throws SQLException
	{
		setStatement.setInt(1, conta.getId());
		setStatement.setLong(2, conta.getSaldo());
		setStatement.setInt(3, conta.getId());
		setStatement.setLong(4, conta.getSaldo());
		setStatement.executeQuery();
	}

	public Conta get(Integer id) throws SQLException
	{
		getStatement.setInt(1, id);
		ResultSet set = getStatement.executeQuery();
		if(set.next())
			return new Conta(set.getInt(1), set.getLong(2));
		else
			return null;
	}
}
