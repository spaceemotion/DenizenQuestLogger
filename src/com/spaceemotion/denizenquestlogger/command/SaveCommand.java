package com.spaceemotion.denizenquestlogger.command;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.spaceemotion.denizenquestlogger.CommandManager;
import com.spaceemotion.denizenquestlogger.PermissionManager;
import com.spaceemotion.denizenquestlogger.config.QuestManager;


public class SaveCommand extends AbstractCommand {
	public SaveCommand(JavaPlugin plugin) {
		super(PermissionManager.ADMIN, plugin);

		description = "Saves the complete quest system to HDD";
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		QuestManager manager = CommandManager.getPlugin().getQuestManager();

		manager.save();

		return true;
	}

}
