package com.spaceemotion.denizenquestlogger.quest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import com.spaceemotion.denizenquestlogger.CommandManager;
import com.spaceemotion.denizenquestlogger.config.QuestManager;
import com.spaceemotion.denizenquestlogger.quest.QuestEntry.QuestState;


public class PlayerEntry {
	private String name;

	private HashMap<QuestEntry.QuestState, List<PlayerQuestEntry>> entries;


	public PlayerEntry(String name) {
		this.name = name;
	}

	public PlayerEntry(Player player) {
		this.name = player.getDisplayName();
	}

	public void load(ConfigurationSection data) {
		entries.put(QuestState.OPEN, addList(data.getConfigurationSection("open"), QuestState.OPEN));
		entries.put(QuestState.FINISHED, addList(data.getConfigurationSection("finished"), QuestState.FINISHED));
	}

	private List<PlayerQuestEntry> addList(ConfigurationSection in, QuestState type) {
		List<PlayerQuestEntry> out = entries.containsKey(type) ? entries.get(type) : new ArrayList<PlayerQuestEntry>();
		
		for (String key : in.getKeys(false)) {
			ConfigurationSection cs = in.getConfigurationSection(key);

			out.add(new PlayerQuestEntry(cs.getInt("id")));
		}
		
		return out;
	}

	public String getPlayerName() {
		return name;
	}

	public HashMap<QuestEntry.QuestState, List<PlayerQuestEntry>> getEntries() {
		return entries;
	}

	public void startQuest(int id) {
		QuestManager questMng = CommandManager.getPlugin().getQuestManager();

		if (questMng.getQuestById(id) != null) {
			List<PlayerQuestEntry> list = entries.get(QuestState.OPEN);

			if (list == null) list = new ArrayList<PlayerQuestEntry>();

			list.add(new PlayerQuestEntry(id));

			entries.put(QuestState.OPEN, list);
		}

		questMng.save();
	}

	public class PlayerQuestEntry {
		private int id;

		public PlayerQuestEntry(int id) {
			setQuestId(id);
		}

		public void setQuestId(int id) {
			this.id = id;
		}

		public int getQuestId() {
			return id;
		}
	}
}
