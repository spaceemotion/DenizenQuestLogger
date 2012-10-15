package com.spaceemotion.denizenquestlogger.config;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.ConfigurationSection;

import com.spaceemotion.denizenquestlogger.CommandManager;
import com.spaceemotion.denizenquestlogger.quest.IllegalQuestException;
import com.spaceemotion.denizenquestlogger.quest.QuestEntry;


public class QuestManager extends ConfigManager {
	public static final String CONFIG_NAME = "quests";

	private List<QuestEntry> questList;

	
	public QuestManager() {
		super(CONFIG_NAME);

		getQuests();
	}

	public List<QuestEntry> getQuests() {
		return getQuests(false);
	}

	public List<QuestEntry> getQuests(boolean forceReload) {
		if (forceReload || questList == null) {
			reload();
			questList = new ArrayList<QuestEntry>();

			for (String key : get().getKeys(false)) {
				try {
					QuestEntry entry = new QuestEntry(get().getConfigurationSection(key));
					questList.add(entry);
				} catch (IllegalQuestException e) {
					CommandManager.getPlugin().getLogger().log(Level.SEVERE, "Could not add quest to list: " + e.getReason());
				}
			}
		}

		return this.questList;
	}

	public int addQuest(QuestEntry entry) {
		if (!questList.contains(entry)) {
			int index = questList.size();
			questList.add(index, entry);

			ConfigurationSection section = get().createSection(Integer.toString(index));
			section.set("name", entry.getName());
			section.set("description", entry.getDescription());

			if (entry.getLocation() != null && !entry.getLocation().isEmpty()) section.set("location", entry.getLocation());

			save();

			return index;
		}

		return -1;
	}

	public void removeQuest(int id) {
		if (questList.get(id) != null) {
			questList.remove(id);

			get().set(Integer.toString(id), null);
			save();
		}
	}

	public QuestEntry getQuestById(int id) {
		return getQuests().get(id);
	}
}
