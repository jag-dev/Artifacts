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
/*    */ public class MercyEffect implements Listener {
/*    */   Main m;
/*    */   
/*    */   public MercyEffect(Main main) {
/* 22 */     this.m = main;
/*    */   }
/*    */   @EventHandler
/*    */   public void onMercy(EntityDamageByEntityEvent e) {
/* 26 */     if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player))
/* 27 */       return;  Player hit = (Player)e.getEntity();
/* 28 */     if (!this.m.activeUsers.containsKey(hit))
/* 29 */       return;  if (hit.hasPermission("artifacts.*") || hit.hasPermission("artifacts.effects") || hit.hasPermission("artifacts.effect.mercy")) {
/* 30 */       String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
/* 31 */       for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 32 */         ItemStack confItem = this.m.getArtifact(key);
/* 33 */         ItemMeta confMeta = confItem.getItemMeta();
/* 34 */         ArtifactActive aa = new ArtifactActive(420);
/* 35 */         confMeta.addEnchant((Enchantment)aa, 1, true);
/* 36 */         confItem.setItemMeta(confMeta);
/* 37 */         if (hit.getInventory().containsAtLeast(confItem, 1))
/* 38 */           for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 39 */             if (effects.equalsIgnoreCase("MERCY")) {
/* 40 */               Random rand = new Random();
/* 41 */               int chance = SettingsFile.getSettings().getConfigurationSection("settings").getInt("mercyChance");
/* 42 */               int duration = SettingsFile.getSettings().getConfigurationSection("settings").getInt("mercyDuration");
/* 43 */               int value = rand.nextInt(chance);
/* 44 */               if (value == 1 || chance == 1) {
/* 45 */                 hit.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration * 20, 1));
/* 46 */                 hit.sendMessage(String.valueOf(prefix) + "Your artifact gave you regeneration");
/*    */               } 
/*    */             } 
/*    */           }  
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\MercyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */