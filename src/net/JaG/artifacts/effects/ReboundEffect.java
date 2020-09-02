/*    */ package net.JaG.artifacts.effects;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.JaG.artifacts.ArtifactActive;
/*    */ import net.JaG.artifacts.Main;
/*    */ import net.JaG.artifacts.SettingsFile;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class ReboundEffect implements Listener {
/*    */   Main m;
/*    */   
/*    */   public ReboundEffect(Main main) {
/* 20 */     this.m = main;
/*    */   }
/*    */   @EventHandler
/*    */   public void onUseRebound(EntityDamageByEntityEvent e) {
/* 24 */     if (!(e.getEntity() instanceof Player))
/* 25 */       return;  Player target = (Player)e.getEntity();
/* 26 */     if (!this.m.activeUsers.containsKey(target))
/* 27 */       return;  if (target.hasPermission("artifacts.*") || target.hasPermission("artifacts.effects") || target.hasPermission("artifacts.effect.rebound")) {
/* 28 */       Random rand = new Random();
/* 29 */       int chance = SettingsFile.getSettings().getConfigurationSection("settings").getInt("reboundChance");
/* 30 */       int value = rand.nextInt(chance);
/* 31 */       if (target.getHealth() <= 2.0D && (
/* 32 */         value == 1 || chance == 1)) {
/* 33 */         ArtifactActive aa = new ArtifactActive(420);
/* 34 */         String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
/* 35 */         for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 36 */           ItemStack confItem = this.m.getArtifact(key);
/* 37 */           ItemMeta confMeta = confItem.getItemMeta();
/* 38 */           confMeta.addEnchant((Enchantment)aa, 1, true);
/* 39 */           confItem.setItemMeta(confMeta);
/* 40 */           if (target.getInventory().containsAtLeast(confItem, 1))
/* 41 */             for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 42 */               if (effects.equalsIgnoreCase("REBOUND")) {
/* 43 */                 target.setHealth(10.0D);
/* 44 */                 target.sendMessage(String.valueOf(prefix) + "Rebound has activated");
/*    */               } 
/*    */             }  
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\ReboundEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */