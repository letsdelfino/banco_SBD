package br.com.SBD.banco_SBD;

import java.sql.*;
import java.util.ArrayList;

public class ContaDAOImplementacao implements ContaDAO {
	protected static final String TABLE_NAME = "accounts";

	private static ContaDAO instance;
	private Database database;
	private PreparedStatement setStatement;
	private PreparedStatement getStatement;
	private PreparedStatement getAllStatement;
	private PreparedStatement idStatement;
	private PreparedStatement deleteStatement;

	private final String SQL_CREATE_TABLE =
		"CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
		+ " `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT, "
		+ " `balance` BIGINT NOT NULL, "
		+ " PRIMARY KEY (`ID`)"
		+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	private final String SQL_GET =
		"SELECT * FROM " + TABLE_NAME + " WHERE ID=?;";
	private final String SQL_GET_ALL =
		"SELECT * FROM " + TABLE_NAME + ";";
	private final String SQL_SET =
		"IF EXISTS (SELECT * FROM " + TABLE_NAME + " WHERE ID=?) THEN"
		+ " UPDATE " + TABLE_NAME + " SET balance=? WHERE ID=?;"
		+ "ELSE"
		+ " INSERT INTO " + TABLE_NAME + " SET ID=?, balance=?;"
		+ "END IF";
	private final String SQL_ID =
		"SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES "
		+ "WHERE TABLE_SCHEMA='" + database.NAME + "' AND "
		+ "TABLE_NAME='" + TABLE_NAME + "';";
	private final String SQL_DELETE =
		"DELETE FROM " + TABLE_NAME + " WHERE ID=?;";

	private ContaDAOImplementacao() throws SQLException, ClassNotFoundException
	{
		database = Database.getInstance();
		createTableIfNotExists();
		prepareStatements();
	}

	public static ContaDAO getInstance()
		throws SQLException, ClassNotFoundException
	{
		if(instance == null)
			instance = new ContaDAOImplementacao();
		return instance;
	}

	private void createTableIfNotExists() throws SQLException
	{
		Statement statement = database.getConnection().createStatement();
		statement.executeUpdate(SQL_CREATE_TABLE);
	}

	private void prepareStatements() throws SQLException
	{
		setStatement = database.getConnection().prepareStatement(SQL_SET);
		getStatement = database.getConnection().prepareStatement(SQL_GET);
		getAllStatement =
			database.getConnection().prepareStatement(SQL_GET_ALL);
		idStatement = database.getConnection().prepareStatement(SQL_ID);
		deleteStatement = database.getConnection().prepareStatement(SQL_DELETE);
	}

	public void set(Conta conta) throws SQLException
	{
		setStatement.setInt(1, conta.getId());
		setStatement.setLong(2, conta.getSaldo());
		setStatement.setInt(3, conta.getId());
		setStatement.setInt(4, conta.getId());
		setStatement.setLong(5, conta.getSaldo());
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

	public ArrayList<Conta> getAll() throws SQLException
	{
		ArrayList<Conta> list = new ArrayList<>();
		ResultSet set = getAllStatement.executeQuery();
		while(set.next())
			list.add(new Conta(set.getInt(1), set.getLong(2)));
		return list;
	}

	public void delete(Integer id) throws SQLException
	{
		deleteStatement.setInt(1, id);
		deleteStatement.executeQuery();
	}

	public Integer getNewId() throws SQLException
	{
		ResultSet set = idStatement.executeQuery();
		set.next();
		return set.getInt(1);
	}
}
