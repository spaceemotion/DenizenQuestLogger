package com.spaceemotion.denizenquestlogger.command;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.spaceemotion.denizenquestlogger.CommandManager;
import com.spaceemotion.denizenquestlogger.DenizenQuestLoggerPlugin;
import com.spaceemotion.denizenquestlogger.PermissionManager;
import com.spaceemotion.denizenquestlogger.quest.QuestEntry;
import com.spaceemotion.denizenquestlogger.util.ChatUtil;
import com.spaceemotion.denizenquestlogger.util.LanguageUtil;
import com.spaceemotion.denizenquestlogger.util.MessageUtil;

public class ListCommand extends AbstractCommand {

	public ListCommand(DenizenQuestLoggerPlugin plugin) {
		super(PermissionManager.ADMIN, plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		List<QuestEntry> entries = CommandManager.getPlugin().getQuestManager().getQuests();

		if (entries == null || entries.size() == 0) {
			ChatUtil.sendPlayerMessage((Player) sender, LanguageUtil.getString("list.empty"));
			return true;
		}

		ChatUtil.sendPlayerMessage((Player) sender, MessageUtil.parseMessage("list.found", Integer.toString(entries.size())));
		
		String messages[] = new String[entries.size()];
		
		for (int i = 0; i < messages.length; i++) {
			QuestEntry e = entries.get(i);
			StringBuilder builder = new StringBuilder();

			builder.append(ChatColor.GOLD + "Name: ").append(ChatColor.RESET + e.getName());
			builder.append(ChatColor.GOLD + " Description: ").append(ChatColor.RESET + e.getDescription());

			messages[i] = MessageUtil.parseColors(builder.toString());
		}
		
		ChatUtil.splitSendMessage(messages, false);

		return true;
	}

}
