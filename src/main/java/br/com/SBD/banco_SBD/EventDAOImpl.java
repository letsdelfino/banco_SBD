package br.com.SBD.banco_SBD;

import java.sql.*;
import java.util.ArrayList;

public class EventDAOImpl implements EventDAO {
	protected static final String TABLE_NAME = "events";

	private static EventDAOImpl instance;
	private Database database;
	private ContaDAO contaDAO;
	private PreparedStatement setStatement;
	private PreparedStatement getStatement;
	private PreparedStatement getAllStatement;
	private PreparedStatement idStatement;
	private PreparedStatement deleteStatement;

	private final String SQL_CREATE_TABLE =
		"CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
		+ " `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT, "
		+ " `action` TINYINT UNSIGNED NOT NULL, "
		+ " `date` DATETIME NOT NULL, "
		+ " `amount` BIGINT NOT NULL, "
		+ " `source_ID` INT UNSIGNED NOT NULL, "
		+ " `destination_ID` INT UNSIGNED NOT NULL, "
		+ " PRIMARY KEY (`ID`)"
		+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	private final String SQL_GET =
		"SELECT * FROM " + TABLE_NAME + " WHERE action=?;";
	private final String SQL_GET_ALL =
		"SELECT * FROM " + TABLE_NAME + " ORDER BY date;";
	private final String SQL_SET =
		"IF EXISTS (SELECT * FROM " + TABLE_NAME + " WHERE ID=?) THEN"
		+ " UPDATE " + TABLE_NAME
		+ " SET action=?, date=?, amount=?, source_ID=?, destination_ID=?"
		+ " WHERE ID=?;"
		+ "ELSE"
		+ " INSERT INTO " + TABLE_NAME
		+ " SET ID=?,"
		+ " action=?, date=?, amount=?, source_ID=?, destination_ID=?;"
		+ "END IF";
	private final String SQL_ID =
		"SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES "
		+ "WHERE TABLE_SCHEMA='" + database.NAME + "' AND "
		+ "TABLE_NAME='" + TABLE_NAME + "';";
	private final String SQL_DELETE =
		"DELETE FROM " + TABLE_NAME + " WHERE ID=?;";

	private EventDAOImpl()
		throws SQLException, ClassNotFoundException
	{
		database = Database.getInstance();
		contaDAO = ContaDAOImplementacao.getInstance();
		createTableIfNotExists();
		prepareStatements();
	}

	public static EventDAO getInstance()
		throws SQLException, ClassNotFoundException
	{
		if(instance == null)
			instance = new EventDAOImpl();
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

	//java.sql.date sets time to zero;
	public void set(Event event) throws SQLException
	{
		setStatement.setInt(1, event.getId());
		setStatement.setInt(2, event.getAction().getId());
		setStatement.setTimestamp(3, new java.sql.Timestamp(event.getDate()
		                                                    .getTime()));
		setStatement.setLong(4, event.getAmount());
		setStatement.setInt(5, event.getSource().getId());
		setStatement.setInt(6, event.getDestination().getId());
		setStatement.setInt(7, event.getId());
		setStatement.setInt(8, event.getId());
		setStatement.setInt(9, event.getAction().getId());
		setStatement.setTimestamp(10, new java.sql.Timestamp(event.getDate()
		                                                     .getTime()));
		setStatement.setLong(11, event.getAmount());
		setStatement.setInt(12, event.getSource().getId());
		setStatement.setInt(13, event.getDestination().getId());
		setStatement.executeQuery();
	}

	public ArrayList<Event> get(OperationType action) throws SQLException
	{
		getStatement.setInt(1, action.getId());
		ArrayList<Event> list = new ArrayList<>();
		ResultSet set = getStatement.executeQuery();
		while(set.next())
			list.add(getFromSet(set));
		return list;
	}

	public ArrayList<Event> getAll() throws SQLException
	{
		ArrayList<Event> list = new ArrayList<>();
		ResultSet set = getAllStatement.executeQuery();
		while(set.next())
			list.add(getFromSet(set));
		return list;
	}

	private Event getFromSet(ResultSet set) throws SQLException
	{
		Event event = new Event(set.getInt(1),
		                        OperationType.byId(set.getInt(2)),
		                        new java.util.Date(set.getTimestamp(3)
		                                           .getTime()),
		                        set.getLong(4), null, null);
		Integer sourceID = set.getInt(5);
		Integer destinationID = set.getInt(6);
		event.setSource(contaDAO.get(sourceID));
		event.setDestination(contaDAO.get(destinationID));
		return event;
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
