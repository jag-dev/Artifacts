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
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class BounceEffect implements Listener {
/*    */   Main m;
/*    */   
/*    */   public BounceEffect(Main main) {
/* 21 */     this.m = main;
/*    */   }
/*    */   @EventHandler
/*    */   public void onBounceEffect(EntityDamageByEntityEvent e) {
/* 25 */     if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player))
/* 26 */       return;  Player target = (Player)e.getDamager();
/* 27 */     Player hit = (Player)e.getEntity();
/* 28 */     if (!this.m.activeUsers.containsKey(hit))
/* 29 */       return;  if (hit.hasPermission("artifacts.*") || hit.hasPermission("artifacts.effects") || hit.hasPermission("artifacts.effect.bounce")) {
/* 30 */       ArtifactActive aa = new ArtifactActive(420);
/* 31 */       String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
/* 32 */       for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 33 */         ItemStack confItem = this.m.getArtifact(key);
/* 34 */         ItemMeta confMeta = confItem.getItemMeta();
/* 35 */         confMeta.addEnchant((Enchantment)aa, 1, true);
/* 36 */         confItem.setItemMeta(confMeta);
/* 37 */         if (hit.getInventory().containsAtLeast(confItem, 1))
/* 38 */           for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 39 */             if (effects.equalsIgnoreCase("BOUNCE")) {
/* 40 */               Random rand = new Random();
/* 41 */               int chance = SettingsFile.getSettings().getConfigurationSection("settings").getInt("bounceChance") - 1;
/* 42 */               int value = rand.nextInt(chance);
/* 43 */               if (value == 1) {
/* 44 */                 Vector v = target.getLocation().getDirection().multiply(-2);
/* 45 */                 v.setY(0);
/* 46 */                 target.setVelocity(v);
/* 47 */                 hit.sendMessage(String.valueOf(prefix) + "You knocked back " + target.getDisplayName());
/* 48 */                 target.sendMessage(String.valueOf(prefix) + "Enemies artifact knocked you back");
/*    */               } 
/*    */             } 
/*    */           }  
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\BounceEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */