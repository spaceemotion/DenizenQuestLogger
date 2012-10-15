package com.spaceemotion.denizenquestlogger.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.spaceemotion.denizenquestlogger.CommandManager;
import com.spaceemotion.denizenquestlogger.PermissionManager;
import com.spaceemotion.denizenquestlogger.config.QuestManager;
import com.spaceemotion.denizenquestlogger.quest.QuestEntry;
import com.spaceemotion.denizenquestlogger.util.ChatUtil;


public class CreateCommand extends AbstractCommand {
	public CreateCommand(JavaPlugin plugin) {
		super(PermissionManager.HELP, plugin);

		description = "Creates a new Quest";
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		QuestManager manager = CommandManager.getPlugin().getQuestManager();

		if (args.length < 3 || args.length > 4) return false;

		QuestEntry entry = new QuestEntry(args[1]);

		entry.setDescription(args[2]);

		if (args.length == 4) entry.setLocation(args[3]);

		manager.addQuest(entry);

		ChatUtil.sendMessage("Quest created: " + ChatColor.GOLD + entry.getName());

		return true;
	}

}
