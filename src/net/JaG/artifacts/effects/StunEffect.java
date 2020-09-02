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
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class StunEffect
/*    */   implements Listener {
/*    */   public StunEffect(Main main) {
/* 21 */     this.m = main;
/*    */   } Main m;
/*    */   @EventHandler
/*    */   public void onFreeze(EntityDamageByEntityEvent e) {
/* 25 */     if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player))
/* 26 */       return;  Player hit = (Player)e.getEntity();
/* 27 */     if (!this.m.activeUsers.containsKey(hit))
/* 28 */       return;  if (hit.hasPermission("artifacts.*") || hit.hasPermission("artifacts.effects") || hit.hasPermission("artifacts.effect.stun")) {
/* 29 */       String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
/* 30 */       for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 31 */         ItemStack confItem = this.m.getArtifact(key);
/* 32 */         ItemMeta confMeta = confItem.getItemMeta();
/* 33 */         ArtifactActive aa = new ArtifactActive(420);
/* 34 */         confMeta.addEnchant((Enchantment)aa, 1, true);
/* 35 */         confItem.setItemMeta(confMeta);
/* 36 */         if (hit.getInventory().containsAtLeast(confItem, 1))
/* 37 */           for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 38 */             if (effects.equalsIgnoreCase("STUN")) {
/* 39 */               Random rand = new Random();
/* 40 */               int chance = SettingsFile.getSettings().getConfigurationSection("settings").getInt("stunChance");
/* 41 */               int duration = SettingsFile.getSettings().getConfigurationSection("settings").getInt("stunDuration");
/* 42 */               int value = rand.nextInt(chance);
/* 43 */               if (value == 1 || chance == 1) {
/* 44 */                 Player dmger = (Player)e.getDamager();
/* 45 */                 dmger.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration * 20, 100));
/* 46 */                 dmger.sendMessage(String.valueOf(prefix) + "You have been stunned by an enemy artifact");
/*    */               } 
/*    */             } 
/*    */           }  
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\StunEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */