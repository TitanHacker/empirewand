package me.Timislol12.empirewand;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public Main plugin;
	public List<String> spells = new ArrayList<String>();
	public getTargets  getTargets = new getTargets();
	public PotionWave PotionWave = new PotionWave(this);
	public Confuse confuse = new Confuse(this);
	
	public void onEnable() {
		plugin = this;
		PluginDescriptionFile pdffile = getDescription();
		Logger logger = Logger.getLogger("Minecraft");
		logger.info(pdffile.getName() + pdffile.getVersion() + "Was enabled.");	
		spells.add("PotionWave");
		spells.add("Confuse");
		
		//you need to register the event
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable() {
		PluginDescriptionFile pdffile = getDescription();
		Logger logger = Logger.getLogger("Minecraft");
		logger.info(pdffile.getName() + pdffile.getVersion() + "Was Disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("ew")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You have to be ingame to use this command");
			}else{
				if(sender.hasPermission("empirewand.use")) {
					Player p = (Player)sender;
					ItemStack stack = new ItemStack(Material.BLAZE_ROD);
					ItemMeta stackMeta = stack.getItemMeta();
					stackMeta.setDisplayName(ChatColor.RED + "Empire" + ChatColor.AQUA + " Wand");
					stack.setItemMeta(stackMeta);
					ChatUtilities.sendMessage(p, "You received the EmpireWand");
					p.getInventory().addItem(stack);
				}else{
					sender.sendMessage(ChatColor.RED + "You dont have permisions");
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
	 if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK ) {
		Player p = e.getPlayer();
		ItemStack stack = p.getItemInHand();
		if(stack != null &&stack.getType() == Material.BLAZE_ROD && stack.hasItemMeta() && stack.getItemMeta().getDisplayName().equals(ChatColor.RED + "Empire" + ChatColor.AQUA + " Wand"));
		int SpellSelected = stack.getDurability();
		if(SpellSelected < 1) {
			stack.setDurability((short)(SpellSelected + 1));
			p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, 119, 30);
		}else {
			stack.setDurability((short) 0);
		}
		ChatUtilities.sendMessage(p, ChatColor.AQUA + spells.get(SpellSelected) + ChatColor.GREEN + " sellected");
		
	 }if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
		  Player p = e.getPlayer();
		  ItemStack stack = p.getItemInHand();
		  if(stack != null &&stack.getType() == Material.BLAZE_ROD && stack.hasItemMeta() && stack.getItemMeta().getDisplayName().equals(ChatColor.RED + "Empire" + ChatColor.AQUA + " Wand"));
		  int SpellSelected = stack.getDurability();
		  if(SpellSelected == 0)
		  this.PotionWave.onCast(p);
	 }else if(SpellSelected == 1){
		 this.Confuse.onCast(p);
	 }

	}



}
