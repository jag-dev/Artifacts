/*    */ package net.JaG.artifacts.effects;
/*    */ 
/*    */ import net.JaG.artifacts.ArtifactActive;
/*    */ import net.JaG.artifacts.Main;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class ImmuneEffect
/*    */   implements Listener {
/*    */   public ImmuneEffect(Main main) {
/* 16 */     this.m = main;
/*    */   } Main m;
/*    */   @EventHandler
/*    */   public void onImmune(EntityDamageEvent e) {
/* 20 */     if (!(e.getEntity() instanceof Player))
/* 21 */       return;  if (e.getEntity() instanceof Player && e.getCause().equals(EntityDamageEvent.DamageCause.POISON)) {
/* 22 */       Player p = (Player)e.getEntity();
/* 23 */       if (!this.m.activeUsers.containsKey(p))
/* 24 */         return;  if (p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.immune"))
/* 25 */         for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 26 */           ItemStack confItem = this.m.getArtifact(key);
/* 27 */           ItemMeta confMeta = confItem.getItemMeta();
/* 28 */           ArtifactActive aa = new ArtifactActive(420);
/* 29 */           confMeta.addEnchant((Enchantment)aa, 1, true);
/* 30 */           confItem.setItemMeta(confMeta);
/*    */           
/* 32 */           if (p.getInventory().containsAtLeast(confItem, 1))
/* 33 */             for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 34 */               if (effects.equalsIgnoreCase("IMMUNE"))
/* 35 */                 e.setCancelled(true); 
/*    */             }  
/*    */         }  
/*    */     } else {
/*    */       return;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\ImmuneEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */