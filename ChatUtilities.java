package me.Timislol12.empirewand;

import static org.bukkit.ChatColor.*;

import org.bukkit.entity.Player;

public class ChatUtilities {

	
	public static void sendMessage(Player p, String msg) {
		p.sendMessage(starter() + msg);
	}
	
	public static String starter() {
		return GOLD + "[" + GRAY + "EmpireWandPlus" + GOLD + "] " + WHITE;
	}
}
