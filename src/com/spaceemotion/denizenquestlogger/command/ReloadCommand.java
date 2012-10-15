package com.spaceemotion.denizenquestlogger.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.spaceemotion.denizenquestlogger.CommandManager;
import com.spaceemotion.denizenquestlogger.DenizenQuestLoggerPlugin;
import com.spaceemotion.denizenquestlogger.PermissionManager;
import com.spaceemotion.denizenquestlogger.util.ChatUtil;
import com.spaceemotion.denizenquestlogger.util.LanguageUtil;

public class ReloadCommand extends AbstractCommand {
	public ReloadCommand(DenizenQuestLoggerPlugin plugin) {
		super(PermissionManager.ADMIN, plugin);

		description = "Reloads config files";
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		DenizenQuestLoggerPlugin plugin = CommandManager.getPlugin();

		plugin.reloadConfig();
		plugin.getQuestManager().getQuests(true);
		plugin.getPlayerManager().getAll(true);

		ChatUtil.sendPlayerMessage((Player) sender, LanguageUtil.getString("reload"));
		return true;
	}

}
