package ml.chriscommunity.economy;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public Economy econ;
	
	@Override
	public void onEnable() {
		econ = new Economy();
		if(econ.initEcon() == 0) {
			Bukkit.getLogger().info("Database connected");
		} else {
			Bukkit.getLogger().info("Database not connected");
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@EventHandler
	void onPlayerJoin (PlayerJoinEvent event) {
		if(!econ.data.exists(event.getPlayer().getUniqueId())) {
			econ.data.createPlayer(event.getPlayer());
		}
	}
	
}
