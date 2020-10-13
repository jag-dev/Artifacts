/*    */ package net.JaG.Artifacts.effects;
/*    */ 
/*    */ import net.JaG.Artifacts.utils.ArtifactActive;
/*    */ import net.JaG.Artifacts.Artifacts;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerItemConsumeEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class FatEffect implements Listener {
/*    */   public FatEffect(Artifacts main) {
/* 17 */     this.m = main;
/*    */   } Artifacts m;
/*    */   @EventHandler
/*    */   public void onFat(PlayerItemConsumeEvent e) {
/* 21 */     Player p = e.getPlayer();
/* 22 */     if (!this.m.activeUsers.containsKey(p))
/* 23 */       return;  if (p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.fat"))
/* 24 */       for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
/* 25 */         ItemStack confItem = this.m.getArtifact(key);
/* 26 */         ItemMeta confMeta = confItem.getItemMeta();
/* 27 */         ArtifactActive aa = new ArtifactActive(420);
/* 28 */         confMeta.addEnchant((Enchantment)aa, 1, true);
/* 29 */         confItem.setItemMeta(confMeta);
/* 30 */         if (p.getInventory().containsAtLeast(confItem, 1))
/* 31 */           for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
/* 32 */             if (effects.equalsIgnoreCase("FAT"))
/* 33 */               p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 6000, 0)); 
/*    */           }  
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\guidenj\Desktop\Desktop\New folder\plugins\Artifacts-v1_6.jar!\net\JaG\artifacts\effects\FatEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */