package me.Timislol12.empirewand;

import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Confuse implements Listener{
	
	private Main plugin;
	
	public Confuse(Main instance) {
		this.plugin = instance;
	}

	public void castSpell(Player p) {
		Location loc = p.getTargetBlock(null, 50).getLocation();
		
		for(Entity e : this.plugin.getTargets.getTargetList(p, loc, 4)) {
			if(e instanceof LivingEntity) {
				((LivingEntity)e).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 600, 1)); 
				((LivingEntity)e).damage(2);
			}
			try{
				FireworkEffectPlayer.playToLocation(loc, FireworkEffect.builder().with(Type.BURST).withColor(Color.fromRGB(3080239)).withFade(Color.fromRGB(4718664)).build());
			}catch(IllegalArgumentException e1) {
				e1.printStackTrace();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			int radius1 = 3;
			int offSetX = loc.getBlockX();
			int offSetY = loc.add(0.0D, 1.0D, 0.0D).getBlockY();
			int offSetZ = loc.getBlockZ();
			World world = loc.getWorld();
			int startX = offSetX - radius1;
			int startY = offSetY - radius1;
			int startZ = offSetZ - radius1;
			int endX = offSetX + radius1;
			int endY = offSetY + radius1;
			int endZ = offSetZ + radius1;
			
			
			for(int counterX = startX; counterX <= endX; counterX++){
				for(int counterY = startY; counterY <= endY; counterY++){
				for(int counterZ = startZ; counterZ <= endZ; counterZ++){
					if((counterX - offSetX ^ 2 + (counterY - offSetY) ^ (counterZ - offSetZ) ^ 0x2) <= 4) {
						Block block = world.getBlockAt(counterX, counterY, counterZ);
						world.playEffect(block.getLocation(), Effect.SMOKE, BlockFace.UP);
					}
				}
				}
			}
		}
	}
}
