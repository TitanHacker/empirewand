package me.Timislol12.empirewand;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class getTargets {
	
	public List<Entity> getTargetList(Player p, Location loc, int radius){
		List<Entity> target = new ArrayList<Entity>();
		int rs = radius * radius;
		Location tmp = new Location(loc.getWorld(), 0.0D, 0.0D, 0.0D);
		for(Entity entity : loc.getWorld().getEntities()){
			if(entity.getLocation(tmp).distance(loc) < rs){
				target.add(entity);
				target.remove(p);
			}
		}
		return target;
	}

}
