/*    */ package net.JaG.artifacts.effects;
/*    */ 
/*    */ import net.JaG.artifacts.ArtifactActive;
/*    */ import net.JaG.artifacts.Main;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class FriendEffect
/*    */   implements Listener {
/*    */   public FriendEffect(Main main) {
/* 16 */     this.m = main;
/*    */   } Main m;
/*    */   @EventHandler
/*    */   public void damageEvent(EntityDamageByEntityEvent e) {
/* 20 */     if (e.getDamager() instanceof Player)
/* 21 */       return;  if (e.getEntity() instanceof Player) {
/* 22 */       Player p = (Player)e.getEntity();
/* 23 */       if (!this.m.activeUsers.containsKey(p))
/* 24 */         return;  if ((p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.friend")) && 
/* 25 */         e.getDamager() instanceof org.bukkit.entity.LivingEntity)
/* 26 */         for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 27 */           ItemStack confItem = this.m.getArtifact(key);
/* 28 */           ItemMeta confMeta = confItem.getItemMeta();
/* 29 */           ArtifactActive aa = new ArtifactActive(420);
/* 30 */           confMeta.addEnchant((Enchantment)aa, 1, true);
/* 31 */           confItem.setItemMeta(confMeta);
/*    */           
/* 33 */           if (p.getInventory().containsAtLeast(confItem, 1))
/* 34 */             for (String effect : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 35 */               if (effect.equalsIgnoreCase("FRIEND"))
/* 36 */                 e.setCancelled(true); 
/*    */             }  
/*    */         }  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\FriendEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */