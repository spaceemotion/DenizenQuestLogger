package com.spaceemotion.denizenquestlogger.denizen;

import java.util.regex.Pattern;

import net.aufdemrand.denizen.commands.AbstractCommand;
import net.aufdemrand.denizen.scripts.ScriptEntry;
import net.citizensnpcs.command.exception.CommandException;

import com.spaceemotion.denizenquestlogger.CommandManager;
import com.spaceemotion.denizenquestlogger.config.PlayerManager;
import com.spaceemotion.denizenquestlogger.config.QuestManager;
import com.spaceemotion.denizenquestlogger.quest.QuestEntry;


/**
 * @author SpaceEmotion
 */
public class QuestDenizenCommand extends AbstractCommand {
	private static final Pattern numberPattern = Pattern.compile("-?\\d+");

	private enum ExecuteType {
		UNKNOWN, START, FINISH
	}

	/* QUEST [START|FINSIH] [ID] (SILENT:Execute silently) */

	/*
	 * Arguments: [] - Required, () - Optional
	 * [START] Start a new quest
	 * [FINISH] End a quest
	 * 
	 * [ID] The quest ID
	 * 
	 * Modifiers:
	 * (SILENT) Execute but give no chat output
	 * 
	 * Example Usage:
	 * QUEST START ID
	 * QUEST FINISH ID
	 */

	@Override
	public boolean execute(ScriptEntry entry) throws CommandException {
		/* Initialize variables */
		ExecuteType type = ExecuteType.UNKNOWN;
		int questId = -1;

		/* Match arguments to expected variables */
		if (entry.arguments() != null) {
			for (String arg : entry.arguments()) {
				// Debug output
				if (plugin.debugMode) plugin.getLogger().info("Processing command " + entry.getCommand() + " argument: " + arg);

				if (arg.equalsIgnoreCase("start")) {
					type = ExecuteType.START;
				} else if (arg.equalsIgnoreCase("finish")) {
					type = ExecuteType.FINISH;
				} else if (isNumber(arg)) {
					questId = Integer.parseInt(arg);
				} else {
					if (plugin.debugMode) plugin.getLogger().info("...unable to match argument!");
				}
			}
		}

		/* Execute the command, if all required variables are filled. */
		if (type != ExecuteType.UNKNOWN && questId != -1) {
			QuestManager questMng = CommandManager.getPlugin().getQuestManager();
			PlayerManager playerMng = CommandManager.getPlugin().getPlayerManager();

			QuestEntry quest = questMng.getQuestById(questId);

			if (quest == null) {
				if (plugin.debugMode) plugin.getLogger().info("Unvalid quest entry (wrong id?)");
				return false;
			}

			switch (type) {
				case START:

					break;

				case FINISH:

					break;

				default:
					break;
			}

			return true;
		}

		/* Error processing */
		throw new CommandException("...not enough arguments! Usage: QUEST [START|FINISH] [ID] (SILENT:Execute silently)");
	}

	private boolean isNumber(String str) {
		return str != null && numberPattern.matcher(str).matches();
	}
}