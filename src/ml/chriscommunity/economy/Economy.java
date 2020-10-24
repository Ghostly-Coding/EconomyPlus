package ml.chriscommunity.economy;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ml.chriscommunity.economy.sql.MySQL;
import ml.chriscommunity.economy.sql.SQLGetter;

public class Economy {
	public MySQL SQL;
	public SQLGetter data;
	
	public int initEcon() {
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
	
	public int depositPlayer(Player player, double coins) {
		if(coins < 0)
			return 1;
		if(!data.exists(player.getUniqueId())) {
			data.createPlayer(player);
		}
		data.setCoins(player.getUniqueId(), coins + data.getCoins(player.getUniqueId()));
		
		return 0;
	}
	
	public int withdrawPlayer(Player player, double coins) {
		if(coins < 0)
			return 1;
		if(getBalance(player) - coins < 0)
			return 1;
		if(!data.exists(player.getUniqueId())) {
			data.createPlayer(player);
		}
		data.setCoins(player.getUniqueId(), data.getCoins(player.getUniqueId()) - coins);
		
		return 0;
	}
	
	public double getBalance(Player player) {
		return data.getCoins(player.getUniqueId());
	}
}
