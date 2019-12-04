package br.com.SBD.banco_SBD;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAOImplementacao implements ClienteDAO {
	protected static final String TABLE_NAME = "clients";

	private static ClienteDAOImplementacao instance;
	private Database database;
	private ContaDAO contaDAO;
	private PreparedStatement setStatement;
	private PreparedStatement getStatement;
	private PreparedStatement getLoginStatement;
	private PreparedStatement getAllStatement;
	private PreparedStatement idStatement;
	private PreparedStatement deleteStatement;

	private final String SQL_CREATE_TABLE =
		"CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
		+ " `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT, "
		+ " `name` VARCHAR(255) NOT NULL, "
		+ " `email` VARCHAR(255) NOT NULL, "
		+ " `login` VARCHAR(255) NOT NULL UNIQUE, "
		+ " `password` VARCHAR(255) NOT NULL, "
		+ " `account_ID` INT UNSIGNED NOT NULL, "
		+ " PRIMARY KEY (`ID`)"
		+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	private final String SQL_GET =
		"SELECT * FROM " + TABLE_NAME + " WHERE ID=?;";
	private final String SQL_GET_LOGIN =
		"SELECT * FROM " + TABLE_NAME + " WHERE login=?;";
	private final String SQL_GET_ALL =
		"SELECT * FROM " + TABLE_NAME + ";";
	private final String SQL_SET =
		"IF EXISTS (SELECT * FROM " + TABLE_NAME + " WHERE ID=?) THEN"
		+ " UPDATE " + TABLE_NAME
		+ " SET name=?, email=?, login=?, password=?, account_ID=? WHERE ID=?;"
		+ "ELSE"
		+ " INSERT INTO " + TABLE_NAME
		+ " SET ID=?, name=?, email=?, login=?, password=?, account_ID=?;"
		+ "END IF";
	private final String SQL_ID =
		"SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES "
		+ "WHERE TABLE_SCHEMA='" + database.NAME + "' AND "
		+ "TABLE_NAME='" + TABLE_NAME + "';";
	private final String SQL_DELETE =
		"DELETE FROM " + TABLE_NAME + " WHERE ID=?;";

	private ClienteDAOImplementacao()
		throws SQLException, ClassNotFoundException
	{
		database = Database.getInstance();
		contaDAO = ContaDAOImplementacao.getInstance();
		createTableIfNotExists();
		prepareStatements();
	}

	public static ClienteDAO getInstance()
		throws SQLException, ClassNotFoundException
	{
		if(instance == null)
			instance = new ClienteDAOImplementacao();
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
		getLoginStatement =
			database.getConnection().prepareStatement(SQL_GET_LOGIN);
		getAllStatement =
			database.getConnection().prepareStatement(SQL_GET_ALL);
		idStatement = database.getConnection().prepareStatement(SQL_ID);
		deleteStatement = database.getConnection().prepareStatement(SQL_DELETE);
	}

	public void set(Cliente cliente) throws SQLException
	{
		setStatement.setInt(1, cliente.getId());
		setStatement.setString(2, cliente.getNome());
		setStatement.setString(3, cliente.getEmail());
		setStatement.setString(4, cliente.getLogin());
		setStatement.setString(5, cliente.getSenha());
		setStatement.setInt(6, cliente.getConta().getId());
		setStatement.setInt(7, cliente.getId());
		setStatement.setInt(8, cliente.getId());
		setStatement.setString(9, cliente.getNome());
		setStatement.setString(10, cliente.getEmail());
		setStatement.setString(11, cliente.getLogin());
		setStatement.setString(12, cliente.getSenha());
		setStatement.setInt(13, cliente.getConta().getId());
		contaDAO.set(cliente.getConta());
		setStatement.executeQuery();
	}

	public Cliente get(Integer id) throws SQLException
	{
		getStatement.setInt(1, id);
		ResultSet set = getStatement.executeQuery();
		if(set.next())
			return getFromSet(set);
		else
			return null;
	}

	public Cliente get(String login) throws SQLException
	{
		getLoginStatement.setString(1, login);
		ResultSet set = getLoginStatement.executeQuery();
		if(set.next())
			return getFromSet(set);
		else
			return null;
	}

	public ArrayList<Cliente> getAll() throws SQLException
	{
		ArrayList<Cliente> list = new ArrayList<>();
		ResultSet set = getAllStatement.executeQuery();
		while(set.next())
			list.add(getFromSet(set));
		return list;
	}

	private Cliente getFromSet(ResultSet set) throws SQLException
	{
			Cliente cliente = new Cliente(set.getInt(1), set.getString(2),
			                   set.getString(3), set.getString(4),
			                   set.getString(5), null);
			Integer accountID = set.getInt(6);
			cliente.setConta(contaDAO.get(accountID));
			return cliente;
	}

	public void delete(Integer id) throws SQLException
	{
		contaDAO.delete(get(id).getConta().getId());
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
