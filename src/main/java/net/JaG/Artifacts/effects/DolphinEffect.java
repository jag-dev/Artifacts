 package net.JaG.Artifacts.effects;

 import net.JaG.Artifacts.utils.ArtifactActive;
 import net.JaG.Artifacts.Artifacts;
 import org.bukkit.Material;
 import org.bukkit.block.Block;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.player.PlayerMoveEvent;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;

 public class DolphinEffect implements Listener {
   Artifacts m;
   public DolphinEffect(Artifacts main) {
/* 18 */     this.m = main;
/*    */   }

   @EventHandler
   public void onDolphin(PlayerMoveEvent e) {
     Player p = e.getPlayer();
     if (!this.m.activeUsers.containsKey(p)) return;
     if (p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.dolphin")) {
       Block current = p.getLocation().add(0.0D, 1.0D, 0.0D).getBlock();
       if (current.getType().equals(Material.STATIONARY_WATER)) {
         for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
           ItemStack confItem = this.m.getArtifact(key);
           ItemMeta confMeta = confItem.getItemMeta();
           ArtifactActive aa = new ArtifactActive(420);
           confMeta.addEnchant(aa, 1, true);
           confItem.setItemMeta(confMeta);

           if (p.getInventory().containsAtLeast(confItem, 1)) {
             for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
               if (effects.equalsIgnoreCase("DOLPHIN")) {
                 p.addAttachment(this.m, "artifacts.dstate", true);
                 if (p.hasPermission("artifacts.dstate")) {
                   p.setAllowFlight(true);
                   p.setFlying(true);
                   p.setFlySpeed(0.04F);

                   return;
                 }
                 return;
               }
             }
           }
         }
       } else if (!current.getType().equals(Material.STATIONARY_WATER) && p.hasPermission("artifacts.dstate")) {
         p.addAttachment(this.m, "artifacts.dstate", false);
         p.setFlying(false);
         p.setAllowFlight(false);
         return;
       }
     }
   }
 }