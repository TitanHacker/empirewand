package me.Timislol12.empirewand;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

public class PotionWave implements Listener{

	public Main plugin;
	
	public PotionWave(Main instance) {
		this.plugin = instance;
	}
	
	public void onCast(final Player p) {
		final BlockIterator blockNext = new BlockIterator(p);
		new BukkitRunnable() {
			public int timer = 0;
			
			public void run() {
				if(this.timer++ > 50) {
					cancel();
				}
				if(blockNext.hasNext()) {
					cancel();
				}
				Block next = blockNext.next();
				try{
					for(Entity e : PotionWave.this.plugin.getTargets.getTargetList(p, next.getLocation(), 3)) {
						if(e instanceof LivingEntity) {
						((LivingEntity)e).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 300, 1));
						
						
						}
				}
				p.removePotionEffect(PotionEffectType.POISON);
				FireworkEffectPlayer.playToLocation(next.getLocation(), FireworkEffect.builder().with(Type.BURST).withColor(Color.LIME).withFade(Color.BLACK).flicker(true).trail(true).build());
			
				
				}catch(IllegalArgumentException e){
					e.printStackTrace();
			}catch(Exception e){
					e.printStackTrace();
			}
			
		}}
		.runTaskTimer(this.plugin, 1L, 1L);
	}
}
