package com.spaceemotion.denizenquestlogger.command;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.spaceemotion.denizenquestlogger.CommandManager;
import com.spaceemotion.denizenquestlogger.PermissionManager;
import com.spaceemotion.denizenquestlogger.util.ChatUtil;
import com.spaceemotion.denizenquestlogger.util.MessageUtil;


public class HelpCommand extends AbstractCommand {
	public HelpCommand(JavaPlugin plugin) {
		super(PermissionManager.HELP, plugin);

		description = "Zeigt die Hilfe an";
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		int size = CommandManager.getIndexList().size();
		ArrayList<String> messages = new ArrayList<String>();

		/* Generate the header */
		String header = "&dQuest-Hilfe";
		String s = "&e============";
		
		messages.add(MessageUtil.parseColors(s + " " + header + " " + s));

		/* Generate the command list */
		for (int i = 0; i < size; ++i) {
			String key = CommandManager.getIndexList().get(i);
			AbstractCommand cmd = CommandManager.getCommandList().get(key);

			if (((Player) sender).hasPermission(cmd.getPermission())) {
				String msg = "&a/quests " + key + " &2" + cmd.getUsage();

				if (!cmd.getDescription().isEmpty()) msg += "&7 " + cmd.getDescription();
				else msg += "&8 (Keine Beschreibung verfŸgbar)";

				messages.add(MessageUtil.parseColors(msg));
			}
		}

		/* And off we go! */
		ChatUtil.splitSendMessage(messages.toArray(new String[0]), false);

		return true;
	}

}
