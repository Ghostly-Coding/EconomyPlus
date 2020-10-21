package ml.chriscommunity.economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public Economy econ;
	
	@Override
	public void onEnable() {
		Economy econ = new Economy();
		if(econ.initEcon() == 0) {
			Bukkit.getLogger().info("Connected to Database Successfully!");
		} else {
			Bukkit.getLogger().info("Failed to connect to Database.");
			this.getServer().getPluginManager().disablePlugin(this);
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
