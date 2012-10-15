package com.spaceemotion.denizenquestlogger.util;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import com.spaceemotion.denizenquestlogger.CommandManager;


public class LanguageUtil {
	public static String getString(String msg) {
		if (CommandManager.getPlugin() != null) {
			FileConfiguration config = CommandManager.getPlugin().getConfig();

			if (config.isConfigurationSection("language")) {
				ConfigurationSection section = config.getConfigurationSection("language");

				if (section.isString(msg)) return section.getString(msg);
			}
		}

		return ("{" + msg + "}").toUpperCase();
	}
}