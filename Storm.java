package me.Timislol12.empirewand;

import java.util.HashSet;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Storm implements Listener{

	private Main plugin;
	
	public Storm(Main instance) {
		this.plugin = instance;
		
	}
	
	public void onCast(Player p) {
		@SuppressWarnings("deprecation")
		Location loc = p.getTargetBlock((HashSet<Byte>) null, 50).getLocation();
		try{
			FireworkEffectPlayer.playToLocation(loc, FireworkEffect.builder().with(Type.BURST).withColor(Color.GREEN).withFade(Color.AQUA).flicker(true).trail(false).build());
			p.getLocation().getWorld().strikeLightning(loc);
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		for(Entity e : this.plugin.getTargets.getTargetList(p, loc, 3)) {
			if(e instanceof LivingEntity) {
				((LivingEntity)e).damage(5);
			}
		}
		
	}
}
