 package net.JaG.artifacts;
 import net.md_5.bungee.api.ChatColor;
 import org.bukkit.Material;
 import org.bukkit.entity.Item;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.inventory.ClickType;
 import org.bukkit.event.inventory.InventoryClickEvent;
 import org.bukkit.event.inventory.InventoryType;
 import org.bukkit.event.player.PlayerDropItemEvent;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.potion.PotionEffect;
 import org.bukkit.potion.PotionEffectType;
 
 public class ArtifactListener implements Listener {
   Main m;
   
   public ArtifactListener(Main main) {
     this.m = main;
   }
   ArtifactListener al;
   @EventHandler
   public void dropArtifact(PlayerDropItemEvent e) {
     Player p = e.getPlayer();
     Item thrown = e.getItemDrop();
     ItemStack thrownStack = thrown.getItemStack();
     String artifactKey = this.m.checkArtifactItemActive(thrownStack, thrownStack.getAmount());
     
     if (artifactKey != "none") {
       ArtifactActive aa = new ArtifactActive(420);
       ItemMeta thrownMeta = thrownStack.getItemMeta();
       thrownMeta.removeEnchant(aa);
       thrownStack.setItemMeta(thrownMeta);
       this.m.toggleActiveItems(p);
       this.m.removeEffects(artifactKey, p);
       p.updateInventory();
       for (PotionEffect key : p.getActivePotionEffects()) {
         if (key.getType().equals(PotionEffectType.INVISIBILITY) && key.getDuration() >= 1000000000) {
           p.removePotionEffect(key.getType());
         }
       } 
     } 
   }
 
   
   @EventHandler
   public void artifactToggle(InventoryClickEvent e) {
     if (e.getClickedInventory() == null || e.getCurrentItem() == null)
       return;  if (e.getClick().equals(ClickType.MIDDLE)) {
       if (e.getClickedInventory().getType().equals(InventoryType.PLAYER) && !e.getCurrentItem().getType().equals(Material.AIR)) {
         Player p = (Player)e.getWhoClicked();
         String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
         ItemStack clickedItem = e.getCurrentItem();
         ItemMeta clickedMeta = clickedItem.getItemMeta();
         ArtifactActive aa = new ArtifactActive(420);
         
         if (clickedItem.containsEnchantment(aa)) {
           String artifactKey = this.m.checkArtifactItemActive(clickedItem, clickedItem.getAmount());
           
           if (artifactKey != "none") {
             clickedMeta.removeEnchant(aa);
             clickedItem.setItemMeta(clickedMeta);
             this.m.toggleActiveItems(p);
             this.m.removeEffects(artifactKey, p);
             p.updateInventory();
             if (p.hasPermission("artifacts.dstate")) {
               p.addAttachment((Plugin)this.m, "artifacts.dstate", false);
               p.setFlying(false);
               p.setAllowFlight(false);
             } 
           } 
         } else {
           
           for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
             boolean isEnabled = this.m.getConfig().getConfigurationSection(key).getBoolean("enabled");
             ItemStack confArtifact = this.m.getArtifact(key);
             ItemMeta artifactMeta = clickedItem.getItemMeta();
             confArtifact.setAmount(clickedItem.getAmount());
             if (clickedItem.equals(confArtifact) && isEnabled) {
               
               if (p.hasPermission("artifacts.use") || p.hasPermission("artifacts.*")) {
                 artifactMeta.addEnchant(aa, 1, true);
                 clickedItem.setItemMeta(artifactMeta);
                 this.m.toggleActiveItems(p);
                 this.m.giveEffects(key, p);
                 p.updateInventory(); continue;
               } 
               p.sendMessage(String.valueOf(prefix) + "You do not have permission to use artifacts");
               continue;
             } 
             if (!isEnabled) p.sendMessage(String.valueOf(prefix) + "This artifact is disabled");
           
           }
         
         } 
       } 
     } else if (e.getClickedInventory().getType().equals(InventoryType.PLAYER) && !e.getCurrentItem().getType().equals(Material.AIR)) {
       Player p = (Player)e.getWhoClicked();
       ItemStack artifact = e.getCurrentItem();
       String artifactKey = this.m.checkArtifactItemActive(artifact, artifact.getAmount());
       
       if (artifactKey != "none") {
         ArtifactActive aa = new ArtifactActive(420);
         ItemMeta artifactMeta = artifact.getItemMeta();
         artifactMeta.removeEnchant(aa);
         artifact.setItemMeta(artifactMeta);
         this.m.toggleActiveItems(p);
         this.m.removeEffects(artifactKey, p);
         p.updateInventory();
         if (p.hasPermission("artifacts.dstate")) {
           p.addAttachment((Plugin)this.m, "artifacts.dstate", false);
           p.setFlying(false);
           p.setAllowFlight(false);
         } 
       } 
     } 
   }
 }
