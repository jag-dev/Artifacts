 package net.JaG.Artifacts.effects;

 import net.JaG.Artifacts.utils.ArtifactActive;
 import net.JaG.Artifacts.Artifacts;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.entity.EntityDamageEvent;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;

 public class ImmuneEffect implements Listener {
   Artifacts m;
   public ImmuneEffect(Artifacts main) {
/* 16 */     this.m = main;
/*    */   }

   @EventHandler
   public void onImmune(EntityDamageEvent e) {
     if (!(e.getEntity() instanceof Player)) return;
     if (e.getEntity() instanceof Player && e.getCause().equals(EntityDamageEvent.DamageCause.POISON)) {
       Player p = (Player)e.getEntity();
       if (!this.m.activeUsers.containsKey(p)) return;
       if (p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.immune"))
         for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
           ItemStack confItem = this.m.getArtifact(key);
           ItemMeta confMeta = confItem.getItemMeta();
           ArtifactActive aa = new ArtifactActive(420);
           confMeta.addEnchant(aa, 1, true);
           confItem.setItemMeta(confMeta);

           if (p.getInventory().containsAtLeast(confItem, 1))
             for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
               if (effects.equalsIgnoreCase("IMMUNE"))
                 e.setCancelled(true);
             }
         }
     } else { return; }
   }
 }