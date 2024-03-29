package br.com.SBD.banco_SBD;

import java.sql.*;

public class Database
{
	private final String HOST = "192.168.1.3";
	private final String USER = "sbd";
	private final String PASS = "ZUgtwC3uTYu2Agrr7PAEV9Hd";
	protected static final String NAME = "sbd";

	private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private final String DB_URL = "jdbc:mariadb://" + HOST + "/" + NAME;

	private static Database instance;
	private static Connection connection;

	private Database() throws ClassNotFoundException, SQLException
	{
		//Throw in case the class is absent
		Class.forName("org.mariadb.jdbc.Driver");
		connection = DriverManager.getConnection(DB_URL, USER, PASS);
	}

	public static Database getInstance()
		throws ClassNotFoundException, SQLException
	{
		if(instance == null)
			instance = new Database();
		return instance;
	}

	public Connection getConnection()
	{
		return connection;
	}
}
