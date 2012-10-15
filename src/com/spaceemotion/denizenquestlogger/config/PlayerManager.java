package com.spaceemotion.denizenquestlogger.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.spaceemotion.denizenquestlogger.CommandManager;

public class PlayerManager {
	private Map<String, ConfigManager> saves;
	
	public PlayerManager() {
		getAll();
	}
	
	public Map<String, ConfigManager> getAll() {
		return getAll(false);
	}

	public Map<String, ConfigManager> getAll(boolean forceReload) {
		if (forceReload || saves == null) {
			saves = load();

			CommandManager.getPlugin().getLogger().info(saves.size() + " player save(s) loaded");
		}

		return this.saves;
	}
	
	private Map<String, ConfigManager> load() {
		Map<String, ConfigManager> map = new HashMap<String, ConfigManager>();
		JavaPlugin plugin = CommandManager.getPlugin();
		
		File playerFolder = new File(plugin.getDataFolder().getPath() + "/players");
		if (!playerFolder.isDirectory() && !playerFolder.mkdirs()) {
		    plugin.getLogger().log(Level.SEVERE, "Could not create player saves folder: " + playerFolder.getPath());
		}
		
		File[] files = playerFolder.listFiles();

		for (File file : files) {
			if (!file.isFile() && !file.getName().endsWith(".yml")) continue;

			String playerName = file.getName().replaceAll(".yml", "");
			
			map.put(playerName, new ConfigManager(file.getName()));
		}

		return map;
	}

	public ConfigManager getPlayer(Player player) {
		return saves.get(player.getDisplayName());
	}

	public ConfigManager getPlayer(String name) {
		return saves.get(name);
	}
}
