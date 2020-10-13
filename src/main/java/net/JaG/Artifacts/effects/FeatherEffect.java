/*    */ package net.JaG.Artifacts.effects;
/*    */ 
/*    */ import net.JaG.Artifacts.utils.ArtifactActive;
/*    */ import net.JaG.Artifacts.Artifacts;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class FeatherEffect implements Listener {
/*    */   Artifacts m;
/*    */   
/*    */   public FeatherEffect(Artifacts main) {
/* 17 */     this.m = main;
/*    */   }
/*    */   @EventHandler
/*    */   public void onFall(EntityDamageEvent e) {
/* 21 */     if (!(e.getEntity() instanceof Player))
/* 22 */       return;  Player p = (Player)e.getEntity();
/* 23 */     if (!this.m.activeUsers.containsKey(p))
/* 24 */       return;  if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
/* 25 */       if (p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.feather"))
/* 26 */         for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 27 */           ItemStack confItem = this.m.getArtifact(key);
/* 28 */           ItemMeta confMeta = confItem.getItemMeta();
/* 29 */           ArtifactActive aa = new ArtifactActive(420);
/* 30 */           confMeta.addEnchant((Enchantment)aa, 1, true);
/* 31 */           confItem.setItemMeta(confMeta);
/* 32 */           if (p.getInventory().containsAtLeast(confItem, 1))
/* 33 */             for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 34 */               if (effects.equalsIgnoreCase("FEATHER")) e.setCancelled(true); 
/*    */             }  
/*    */         }  
/*    */     } else {
/*    */       return;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\FeatherEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */