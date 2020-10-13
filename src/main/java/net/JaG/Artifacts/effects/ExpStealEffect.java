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
/*    */ 
/*    */ public class ExpStealEffect
/*    */   implements Listener {
/*    */   public ExpStealEffect(Artifacts main) {
/* 19 */     this.m = main;
/*    */   } Artifacts m;
/*    */   @EventHandler
/*    */   public void onSteal(EntityDamageByEntityEvent e) {
/* 23 */     if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player))
/* 24 */       return;  Player dmgr = (Player)e.getDamager();
/* 25 */     if (!this.m.activeUsers.containsKey(dmgr))
/* 26 */       return;  if (dmgr.hasPermission("artifacts.*") || dmgr.hasPermission("artifacts.effects") || dmgr.hasPermission("artifacts.effect.expsteal")) {
/* 27 */       String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
/* 28 */       for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 29 */         ItemStack confItem = this.m.getArtifact(key);
/* 30 */         ItemMeta confMeta = confItem.getItemMeta();
/* 31 */         ArtifactActive aa = new ArtifactActive(420);
/* 32 */         confMeta.addEnchant((Enchantment)aa, 1, true);
/* 33 */         confItem.setItemMeta(confMeta);
/* 34 */         if (dmgr.getInventory().containsAtLeast(confItem, 1))
/* 35 */           for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 36 */             if (effects.equalsIgnoreCase("EXP_STEAL")) {
/* 37 */               Random rand = new Random();
/* 38 */               int chance = SettingsFile.getSettings().getConfigurationSection("settings").getInt("expStealChance");
/* 39 */               int value = rand.nextInt(chance);
/* 40 */               if (value == 1 || chance == 1) {
/* 41 */                 Player enemy = (Player)e.getEntity();
/* 42 */                 int enemy_exp = enemy.getTotalExperience();
/* 43 */                 dmgr.giveExp(enemy_exp);
/* 44 */                 enemy.setExp(0.0F);
/* 45 */                 enemy.setLevel(0);
/* 46 */                 dmgr.sendMessage(String.valueOf(prefix) + "Stole enemies experience");
/*    */               } 
/*    */             } 
/*    */           }  
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\ExpStealEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */