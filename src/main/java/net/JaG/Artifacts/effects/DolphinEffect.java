/*    */ package net.JaG.Artifacts.effects;
/*    */ 
/*    */ import net.JaG.Artifacts.utils.ArtifactActive;
/*    */ import net.JaG.Artifacts.Artifacts;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class DolphinEffect implements Listener {
/*    */   public DolphinEffect(Artifacts main) {
/* 18 */     this.m = main;
/*    */   } Artifacts m;
/*    */   @EventHandler
/*    */   public void onDolphin(PlayerMoveEvent e) {
/* 22 */     Player p = e.getPlayer();
/* 23 */     if (!this.m.activeUsers.containsKey(p))
/* 24 */       return;  if (p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.dolphin")) {
/* 25 */       Block current = p.getLocation().add(0.0D, 1.0D, 0.0D).getBlock();
/* 26 */       if (current.getType().equals(Material.STATIONARY_WATER)) {
/* 27 */         for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 28 */           ItemStack confItem = this.m.getArtifact(key);
/* 29 */           ItemMeta confMeta = confItem.getItemMeta();
/* 30 */           ArtifactActive aa = new ArtifactActive(420);
/* 31 */           confMeta.addEnchant((Enchantment)aa, 1, true);
/* 32 */           confItem.setItemMeta(confMeta);
/*    */           
/* 34 */           if (p.getInventory().containsAtLeast(confItem, 1)) {
/* 35 */             for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 36 */               if (effects.equalsIgnoreCase("DOLPHIN")) {
/* 37 */                 p.addAttachment((Plugin)this.m, "artifacts.dstate", true);
/* 38 */                 if (p.hasPermission("artifacts.dstate")) {
/* 39 */                   p.setAllowFlight(true);
/* 40 */                   p.setFlying(true);
/* 41 */                   p.setFlySpeed(0.04F);
/*    */                   
/*    */                   return;
/*    */                 } 
/*    */                 return;
/*    */               } 
/*    */             } 
/*    */           }
/*    */         } 
/* 50 */       } else if (!current.getType().equals(Material.STATIONARY_WATER) && 
/* 51 */         p.hasPermission("artifacts.dstate")) {
/* 52 */         p.addAttachment((Plugin)this.m, "artifacts.dstate", false);
/* 53 */         p.setFlying(false);
/* 54 */         p.setAllowFlight(false);
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\DolphinEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */