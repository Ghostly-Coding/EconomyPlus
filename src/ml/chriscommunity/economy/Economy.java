package ml.chriscommunity.economy;

import java.sql.SQLException;

import org.bukkit.Bukkit;

import ml.chriscommunity.economy.sql.MySQL;
import ml.chriscommunity.economy.sql.SQLGetter;

public class Economy {
	public MySQL SQL;
	public SQLGetter data;
	
	int initEcon() {
		this.SQL = new MySQL();
		this.data = new SQLGetter(this);
		
		try {
			SQL.connect();
		} catch (ClassNotFoundException | SQLException e) {
			//e.printStackTrace();
			// Login info is incorrect
			// they are not using a database
			return 1;
		}
		
		if(SQL.isConnected()) {
			data.createTable();
		} else {
			return 1;
		}
		
		return 0;
	}
}
