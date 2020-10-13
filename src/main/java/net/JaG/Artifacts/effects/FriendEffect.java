 package net.JaG.Artifacts.effects;

 import net.JaG.Artifacts.utils.ArtifactActive;
 import net.JaG.Artifacts.Artifacts;
 import org.bukkit.entity.LivingEntity;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.entity.EntityDamageByEntityEvent;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;

 public class FriendEffect
   implements Listener {
   public FriendEffect(Artifacts main) {
/* 16 */     this.m = main;
/*    */   } Artifacts m;
   @EventHandler
   public void damageEvent(EntityDamageByEntityEvent e) {
     if (e.getDamager() instanceof Player) return;
     if (e.getEntity() instanceof Player) {
       Player p = (Player)e.getEntity();
       if (!this.m.activeUsers.containsKey(p)) return;
       if ((p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.friend")) && e.getDamager() instanceof LivingEntity)
         for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
           ItemStack confItem = this.m.getArtifact(key);
           ItemMeta confMeta = confItem.getItemMeta();
           ArtifactActive aa = new ArtifactActive(420);
           confMeta.addEnchant(aa, 1, true);
           confItem.setItemMeta(confMeta);

           if (p.getInventory().containsAtLeast(confItem, 1))
             for (String effect : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
               if (effect.equalsIgnoreCase("FRIEND"))
                 e.setCancelled(true);
             }
         }
     }
   }
 }