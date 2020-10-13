 package net.JaG.Artifacts.effects;

 import net.JaG.Artifacts.utils.ArtifactActive;
 import net.JaG.Artifacts.Artifacts;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.player.PlayerItemDamageEvent;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;

 public class BlacksmithEffect implements Listener {
   Artifacts m;
   public BlacksmithEffect(Artifacts main) {
/* 15 */     this.m = main;
/*    */   }

   @EventHandler
   public void onBlacksmith(PlayerItemDamageEvent e) {
     if (!e.getItem().equals(e.getPlayer().getItemInHand())) return;
     if (!this.m.activeUsers.containsKey(e.getPlayer())) return;
     Player p = e.getPlayer();

     if (p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.blacksmith"))
       for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
         ItemStack confItem = this.m.getArtifact(key);
         ItemMeta confMeta = confItem.getItemMeta();
         ArtifactActive aa = new ArtifactActive(420);
         confMeta.addEnchant(aa, 1, true);
         confItem.setItemMeta(confMeta);
         if (p.getInventory().containsAtLeast(confItem, 1))
           for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
             if (effects.equalsIgnoreCase("BLACKSMITH")) {
               ItemStack item = p.getItemInHand();
               if (item.getDurability() < item.getType().getMaxDurability()) {
                 e.setCancelled(true);
                 item.setDurability((short)(item.getDurability() - 1));
                 p.updateInventory();
               }
             }
           }
       }
   }
 }