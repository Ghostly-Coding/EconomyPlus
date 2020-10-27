package ml.chriscommunity.economy.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
	
	private String host = "107.180.40.196";
	private String port = "3306";
	private String database = "ccbdb";
	private String username = "ccnusr";
	private String password = "6VcVZuDqC3ns5c7";
	
	private Connection connection;
	
	public boolean isConnected() {
		return connection != null;
	}
	
	public void connect() throws ClassNotFoundException, SQLException {
		if (!isConnected()) {
			connection = DriverManager.getConnection("jdbc:mysql://" + 
					host + ":" + port + "/" + database + "?useSSL=false",
					username, password);
		}
	}
	
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
}
