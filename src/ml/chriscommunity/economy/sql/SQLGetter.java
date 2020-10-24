package ml.chriscommunity.economy.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;

import ml.chriscommunity.economy.Economy;

public class SQLGetter {
	
	private Economy plugin;
	public SQLGetter(Economy plugin) {
		this.plugin = plugin;
	}
	
	public void createTable() {
		PreparedStatement ps;
		try {
			ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS economy "
					+ "(UUID VARCHAR(100), COINS INT(100), PRIMARY KEY (UUID))");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createPlayer(Player player) {
		try {
			UUID uuid = player.getUniqueId();
			if(!exists(uuid)) {
				PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO economy"
						+ " (UUID) VALUES (?)");
				ps2.setString(1, uuid.toString());
				ps2.executeUpdate();
				
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean exists(UUID uuid) {
		try {
			PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM economy WHERE UUID=?");
			ps.setString(1, uuid.toString());
			
			ResultSet results = ps.executeQuery();
			if(results.next()) {
				// player is found
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void setCoins(UUID uuid, double coins) {
		try {
			PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE economy SET COINS=? WHERE UUID=?");
			ps.setDouble(1, (coins));
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public double getCoins(UUID uuid) {
		try {
			PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT COINS FROM economy WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			double points = 0;
			if (rs.next()) {
				points = rs.getDouble("COINS");
				return points;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
}
