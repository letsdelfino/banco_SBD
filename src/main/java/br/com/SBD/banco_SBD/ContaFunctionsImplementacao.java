package br.com.SBD.banco_SBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContaFunctionsImplementacao implements ContaFunctions{
	protected static final String TABLE_NAME = "accounts";
	
	private Database database;
	private ContaFunctions contaFunctions;
	private static ClienteDAOImplementacao instance;
	private PreparedStatement setStatement;
	private PreparedStatement getStatement;
	private PreparedStatement getLoginStatement;
	private PreparedStatement getAllStatement;
	private PreparedStatement idStatement;
	private PreparedStatement deleteStatement;
	
	private ContaFunctionsImplementacao()
			throws SQLException, ClassNotFoundException
		{
			database = Database.getInstance();
			contaFunctions = ContaFunctionsImplementacao.getInstance();
			prepareStatements();
		}

		public static ContaFunctions getInstance()
			throws SQLException, ClassNotFoundException
		{
			if(instance == null)
				instance = new ContaFunctionsImplementacao();
			return instance;
		}
	
} 
