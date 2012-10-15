package com.spaceemotion.denizenquestlogger;

import java.rmi.activation.ActivationException;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import com.spaceemotion.denizenquestlogger.config.PlayerManager;
import com.spaceemotion.denizenquestlogger.config.QuestManager;
import com.spaceemotion.denizenquestlogger.denizen.QuestDenizenCommand;


/**
 * DenizenQuestLogger Main class
 * 
 * @author SpaceEmotion
 * @version 1.0
 */
public class DenizenQuestLoggerPlugin extends JavaPlugin {
	private QuestDenizenCommand questCommand;

	private QuestManager questManager;
	private PlayerManager playerManager;

	/**
	 * Enables the plugin
	 * 
	 * @return void
	 */
	public void onEnable() {
		/* set up command executor */
		getCommand("quests").setExecutor(new CommandManager(this));

		/* set up configuration file(s) */
		getConfig().options().copyDefaults(true);
		this.saveConfig();

		questManager = new QuestManager();
		playerManager = new PlayerManager();

		questCommand = new QuestDenizenCommand();

		try {
			questCommand.activateAs("QUEST");
		} catch (ActivationException e) {
			getLogger().log(Level.SEVERE, "Oh no! Could not activate denizen command!");
			e.printStackTrace();

			getServer().getPluginManager().disablePlugin(this);
		}

		/* Off we go! */
		getLogger().info("Activated " + this.getPluginInformationString());
	}

	/**
	 * Disables the plugin
	 * 
	 * @return void
	 */
	public void onDisable() {
		questManager.save();

		getLogger().info("Disabled " + this.getPluginInformationString());
	}

	/**
	 * Returns plugin name and version as combined String
	 * 
	 * @return String
	 */
	protected String getPluginInformationString() {
		return getDescription().getName() + " v" + getDescription().getVersion();
	}

	public QuestManager getQuestManager() {
		return questManager;
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}
}
