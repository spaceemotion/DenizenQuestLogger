package com.spaceemotion.denizenquestlogger.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.spaceemotion.denizenquestlogger.CommandManager;


public class ConfigManager {
	private FileConfiguration config;
	private File file = null;

	private String name;

	public ConfigManager(String name) {
		this.name = name;
	}

	public void reload() {
		if (file == null) file = new File(CommandManager.getPlugin().getDataFolder(), name + ".yml");

		config = YamlConfiguration.loadConfiguration(file);

		// Look for defaults in the jar
		InputStream defConfigStream = CommandManager.getPlugin().getResource(name + ".yml");
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			config.setDefaults(defConfig);
		}
	}

	public FileConfiguration get() {
		if (config == null) this.reload();

		return config;
	}

	public void save() {
		if (config == null || file == null) return;

		try {
			get().save(file);
		} catch (IOException e) {
			CommandManager.getPlugin().getLogger().log(Level.SEVERE, "Could not save config to " + file, e);
		}
	}

	public String getName() {
		return name;
	}
}
