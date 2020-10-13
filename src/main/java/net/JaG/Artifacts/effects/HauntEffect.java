/*    */ package net.JaG.Artifacts.effects;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.JaG.Artifacts.utils.ArtifactActive;
/*    */ import net.JaG.Artifacts.Artifacts;
/*    */ import net.JaG.Artifacts.utils.SettingsFile;
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
/*    */ public class HauntEffect implements Listener {
/*    */   Artifacts m;
/*    */   
/*    */   public HauntEffect(Artifacts main) {
/* 22 */     this.m = main;
/*    */   }
/*    */   @EventHandler
/*    */   public void onHauntEffect(EntityDamageByEntityEvent e) {
/* 26 */     if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player))
/* 27 */       return;  Player target = (Player)e.getDamager();
/* 28 */     Player hit = (Player)e.getEntity();
/* 29 */     if (!this.m.activeUsers.containsKey(hit))
/* 30 */       return;  if (hit.hasPermission("artifacts.*") || hit.hasPermission("artifacts.effects") || hit.hasPermission("artifacts.effect.haunt")) {
/* 31 */       String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
/* 32 */       for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 33 */         ItemStack confItem = this.m.getArtifact(key);
/* 34 */         ItemMeta confMeta = confItem.getItemMeta();
/* 35 */         ArtifactActive aa = new ArtifactActive(420);
/* 36 */         confMeta.addEnchant((Enchantment)aa, 1, true);
/* 37 */         confItem.setItemMeta(confMeta);
/* 38 */         if (hit.getInventory().containsAtLeast(confItem, 1))
/* 39 */           for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 40 */             if (effects.equalsIgnoreCase("HAUNT")) {
/* 41 */               Random rand = new Random();
/* 42 */               int chance = SettingsFile.getSettings().getConfigurationSection("settings").getInt("hauntChance");
/* 43 */               int value = rand.nextInt(chance);
/* 44 */               if (value == 1 || chance == 1) {
/* 45 */                 target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
/* 46 */                 hit.sendMessage(String.valueOf(prefix) + "You applied blindness to " + target.getDisplayName() + " for 5s");
/* 47 */                 target.sendMessage(String.valueOf(prefix) + "Enemies artifact blinded you");
/*    */               } 
/*    */             } 
/*    */           }  
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\HauntEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */