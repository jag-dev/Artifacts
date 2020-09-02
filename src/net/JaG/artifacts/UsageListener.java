 package net.JaG.artifacts;
 import net.md_5.bungee.api.ChatColor;
 import org.bukkit.Material;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.block.Action;
 import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
 import org.bukkit.event.player.PlayerItemConsumeEvent;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 
 public class UsageListener implements Listener {
   Main m;
   
   public UsageListener(Main main) {
     this.m = main;
   }
   
   @EventHandler
   public void onPlace(BlockPlaceEvent e) {
     Player p = e.getPlayer();
     String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
     
     for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
       ItemStack confItemOff = this.m.getArtifact(key);
       ItemMeta confMeta = confItemOff.getItemMeta();
       confItemOff.setItemMeta(confMeta);
       
       ItemStack confItemOn = this.m.getArtifact(key);
       ItemMeta confMetaOn = confItemOn.getItemMeta();
       ArtifactActive aa = new ArtifactActive(420);
       confMetaOn.addEnchant(aa, 1, true);
       confItemOn.setItemMeta(confMetaOn);
       
       if (p.getItemInHand().isSimilar(confItemOff) || p.getItemInHand().isSimilar(confItemOn)) {
         e.setCancelled(true);
         p.sendMessage(String.valueOf(prefix) + "You can't place an artifact");
       } 
     } 
   }
 
   
   @EventHandler
   public void onEat(PlayerItemConsumeEvent e) {
     Player p = e.getPlayer();
     String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
     for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
       ItemStack confItemOff = this.m.getArtifact(key);
       ItemMeta confMetaOff = confItemOff.getItemMeta();
       confItemOff.setItemMeta(confMetaOff);
       
       ItemStack confItemOn = this.m.getArtifact(key);
       ItemMeta confMetaOn = confItemOn.getItemMeta();
       ArtifactActive aa = new ArtifactActive(420);
       confMetaOn.addEnchant(aa, 1, true);
       confItemOn.setItemMeta(confMetaOn);
       
       if (p.getItemInHand().isSimilar(confItemOff) || p.getItemInHand().isSimilar(confItemOn)) {
         e.setCancelled(true);
         p.sendMessage(String.valueOf(prefix) + "You can't eat an artifact");
       } 
     } 
   }
 
   
   @EventHandler
   public void onThrow(PlayerInteractEvent e) {
     if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
       Player p = e.getPlayer();
       if (p.getItemInHand() != null) {
         String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
         for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
           if (p.getItemInHand().getType().equals(Material.ENDER_PEARL) || 
             p.getItemInHand().getType().equals(Material.EYE_OF_ENDER) || 
             p.getItemInHand().getType().equals(Material.SNOW_BALL) || 
             p.getItemInHand().getType().equals(Material.EXP_BOTTLE)) {
             
             ItemStack confItemOff = this.m.getArtifact(key);
             ItemMeta confMetaOff = confItemOff.getItemMeta();
             confItemOff.setItemMeta(confMetaOff);
             
             ItemStack confItemOn = this.m.getArtifact(key);
             ItemMeta confMetaOn = confItemOn.getItemMeta();
             ArtifactActive aa = new ArtifactActive(420);
             confMetaOn.addEnchant(aa, 1, true);
             confItemOn.setItemMeta(confMetaOn);
             
             if (p.getItemInHand().isSimilar(confItemOff) || p.getItemInHand().isSimilar(confItemOn)) {
               e.setCancelled(true);
               p.sendMessage(String.valueOf(prefix) + "You can't throw artifacts");
               p.updateInventory();
             }  continue;
           }  if (p.getItemInHand().getType().equals(Material.POTION)) {
             ItemStack confItemOff = this.m.getArtifact(key);
             ItemMeta confMetaOff = confItemOff.getItemMeta();
             confItemOff.setItemMeta(confMetaOff);
             
             ItemStack confItemOn = this.m.getArtifact(key);
             ItemMeta confMetaOn = confItemOn.getItemMeta();
             ArtifactActive aa = new ArtifactActive(420);
             confMetaOn.addEnchant(aa, 1, true);
             confItemOn.setItemMeta(confMetaOn);
             
             if (p.getItemInHand().isSimilar(confItemOff) || p.getItemInHand().isSimilar(confItemOn)) {
               e.setCancelled(true);
               p.sendMessage(String.valueOf(prefix) + "You can't use artifacts");
               p.updateInventory();
             } 
           } 
         } 
       } 
     } 
   }
   
   @EventHandler
   public void onEmpty(PlayerBucketEmptyEvent e) {
	     Player p = e.getPlayer();
	     String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
		 for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
			   ItemStack confItemOff = this.m.getArtifact(key);
			   ItemMeta confMetaOff = confItemOff.getItemMeta();
			   confItemOff.setItemMeta(confMetaOff);
			   
			   ItemStack confItemOn = this.m.getArtifact(key);
			   ItemMeta confMetaOn = confItemOn.getItemMeta();
			   ArtifactActive aa = new ArtifactActive(420);
			   confMetaOn.addEnchant(aa, 1, true);
			   confItemOn.setItemMeta(confMetaOn);
			   
				if (p.getItemInHand().isSimilar(confItemOff) || p.getItemInHand().isSimilar(confItemOn)) {
					  e.setCancelled(true);
					  p.sendMessage(String.valueOf(prefix) + "You can't place an artifact");
				} 
		} 
   }
   
   
 }
