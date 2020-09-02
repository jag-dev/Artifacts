/*    */ package net.JaG.artifacts.effects;
/*    */ 
/*    */ import net.JaG.artifacts.ArtifactActive;
/*    */ import net.JaG.artifacts.Main;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerItemDamageEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class BlacksmithEffect implements Listener {
/*    */   public BlacksmithEffect(Main main) {
/* 15 */     this.m = main;
/*    */   } Main m;
/*    */   @EventHandler
/*    */   public void onBlacksmith(PlayerItemDamageEvent e) {
/* 19 */     if (!e.getItem().equals(e.getPlayer().getItemInHand()))
/* 20 */       return;  if (!this.m.activeUsers.containsKey(e.getPlayer()))
/* 21 */       return;  Player p = e.getPlayer();
/* 22 */     if (p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.blacksmith"))
/* 23 */       for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 24 */         ItemStack confItem = this.m.getArtifact(key);
/* 25 */         ItemMeta confMeta = confItem.getItemMeta();
/* 26 */         ArtifactActive aa = new ArtifactActive(420);
/* 27 */         confMeta.addEnchant((Enchantment)aa, 1, true);
/* 28 */         confItem.setItemMeta(confMeta);
/* 29 */         if (p.getInventory().containsAtLeast(confItem, 1))
/* 30 */           for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 31 */             if (effects.equalsIgnoreCase("BLACKSMITH")) {
/* 32 */               ItemStack item = p.getItemInHand();
/* 33 */               if (item.getDurability() < item.getType().getMaxDurability()) {
/* 34 */                 e.setCancelled(true);
/* 35 */                 item.setDurability((short)(item.getDurability() - 1));
/* 36 */                 p.updateInventory();
/*    */               } 
/*    */             } 
/*    */           }  
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\BlacksmithEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */