/*    */ package net.JaG.artifacts.effects;
/*    */ 
/*    */ import net.JaG.artifacts.ArtifactActive;
/*    */ import net.JaG.artifacts.Main;
/*    */ import net.JaG.artifacts.SettingsFile;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerToggleSneakEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class NinjaEffect implements Listener {
/*    */   public NinjaEffect(Main main) {
/* 21 */     this.m = main;
/*    */   } Main m;
/*    */   @EventHandler
/*    */   public void onUseNinja(PlayerToggleSneakEvent e) {
/* 25 */     final Player p = e.getPlayer();
/* 26 */     if (!this.m.activeUsers.containsKey(p))
/*    */       return; 
/* 28 */     if (p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.ninja")) {
/* 29 */       final PotionEffect camo = new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, 1);
/* 30 */       if (!p.isSneaking()) {
/*    */         
/* 32 */         final String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
/* 33 */         for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 34 */           ItemStack confItem = this.m.getArtifact(key);
/* 35 */           ItemMeta confMeta = confItem.getItemMeta();
/* 36 */           ArtifactActive aa = new ArtifactActive(420);
/* 37 */           confMeta.addEnchant((Enchantment)aa, 1, true);
/* 38 */           confItem.setItemMeta(confMeta);
/* 39 */           if (p.getInventory().containsAtLeast(confItem, 1)) {
/* 40 */             for (String effect : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 41 */               if (effect.equalsIgnoreCase("NINJA")) {
/* 42 */                 (new BukkitRunnable() {
/* 43 */                     int timer = SettingsFile.getSettings().getConfigurationSection("settings").getInt("ninjaSeconds") - 1;
/*    */                     public void run() {
/* 45 */                       if (this.timer >= 0) {
/* 46 */                         if (p.isSneaking()) {
/* 47 */                           p.sendMessage(String.valueOf(prefix) + "Invisibility enabling in: " + (this.timer + 1));
/* 48 */                           this.timer--;
/* 49 */                         } else if (!p.isSneaking()) {
/* 50 */                           p.sendMessage(String.valueOf(prefix) + "Please remain sneaking to gain effect");
/* 51 */                           cancel();
/*    */                         } 
/*    */                       } else {
/*    */                         
/* 55 */                         if (p.isSneaking()) {
/* 56 */                           p.addPotionEffect(camo);
/* 57 */                           p.sendMessage(String.valueOf(prefix) + "You are now invisible");
/*    */                         } 
/* 59 */                         cancel();
/*    */                       } 
/*    */                     }
/* 62 */                   }).runTaskTimer((Plugin)this.m, 0L, 20L);
/*    */               }
/*    */             }
/*    */           
/*    */           }
/*    */         } 
/* 68 */       } else if (p.isSneaking()) {
/*    */         
/* 70 */         final String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
/* 71 */         for (PotionEffect effect : p.getActivePotionEffects()) {
/* 72 */           if (effect.getType().equals(PotionEffectType.INVISIBILITY) && effect.getDuration() >= 1000000000) {
/* 73 */             p.removePotionEffect(PotionEffectType.INVISIBILITY);
/* 74 */             p.sendMessage(String.valueOf(prefix) + "Invisibility has been removed");
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\NinjaEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */