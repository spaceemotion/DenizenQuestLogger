package com.spaceemotion.denizenquestlogger.quest;

import org.bukkit.configuration.ConfigurationSection;


public class QuestEntry {
	public enum QuestState {
		UNKNOWN, OPEN, FINISHED
	}

	public enum Priority {
		UNKNOWN, DEFAULT, NEEDED
	}

	private String name;
	private String desc;
	private String loc;

	private QuestState state;

	private Priority priority;


	public QuestEntry(String name) {
		this(name, QuestState.UNKNOWN);
	}

	public QuestEntry(String name, QuestState state) {
		this.name = name;
		this.desc = "";
		this.priority = Priority.DEFAULT;
		this.state = state;
	}

	public QuestEntry(ConfigurationSection section) throws IllegalQuestException {
		if (!section.contains("name")) throw new IllegalQuestException("No quest name found");
		this.name = section.getString("name");

		if (!section.contains("description")) throw new IllegalQuestException("No quest description found");
		this.desc = section.getString("description");

		this.loc = section.getString("location", "");

		this.state = QuestState.UNKNOWN;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return desc;
	}

	public String getLocation() {
		return loc;
	}

	public QuestState getState() {
		return state;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setName(String n) {
		this.name = n;
	}

	public void setDescription(String d) {
		this.desc = d;
	}

	public void setLocation(String l) {
		this.loc = l;
	}

	public void setState(QuestState s) {
		this.state = s;
	}

	public void setPriority(Priority p) {
		this.priority = p;
	}
}
