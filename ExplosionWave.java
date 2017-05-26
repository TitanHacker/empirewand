package me.Timislol12.empirewand;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

public class ExplosionWave implements Listener{
	
	private Main plugin;
	
	public ExplosionWave (Main instance){
		this.plugin = instance;
	}
	
	public void onCast(Player p) {
		final BlockIterator blockNext = new BlockIterator(p);
		new BukkitRunnable(){
			public int timer = 0;
			
			public void run(){
			if(timer == 50)	{
				cancel();
			}
				if(!blockNext.hasNext()){
					cancel();
				}
				Block next = blockNext.next();
				try{
					next.getWorld().createExplosion(next.getLocation(), 3);
					FireworkEffectPlayer.playToLocation(next.getLocation(), FireworkEffect.builder().with(Type.BALL_LARGE).withColor(Color.BLACK).withFade(Color.PURPLE).flicker(true).withColor(Color.PURPLE).withFade(Color.BLACK).build());
				}catch(IllegalArgumentException e){
					e.printStackTrace();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		.runTaskTimer(plugin, 1L, 1l);
	}

}
