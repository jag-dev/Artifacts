 package net.JaG.Artifacts.effects;

 import java.util.Random;
 import net.JaG.Artifacts.utils.ArtifactActive;
 import net.JaG.Artifacts.Artifacts;
 import net.JaG.Artifacts.utils.SettingsFile;
 import net.md_5.bungee.api.ChatColor;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.entity.EntityDamageByEntityEvent;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 import org.bukkit.util.Vector;

 public class BounceEffect implements Listener {
   Artifacts m;
   public BounceEffect(Artifacts main) {
/* 21 */     this.m = main;
/*    */   }

   @EventHandler
   public void onBounceEffect(EntityDamageByEntityEvent e) {
     if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
     Player target = (Player)e.getDamager();
     Player hit = (Player)e.getEntity();
     if (!this.m.activeUsers.containsKey(hit)) return;

     if (hit.hasPermission("artifacts.*") || hit.hasPermission("artifacts.effects") || hit.hasPermission("artifacts.effect.bounce")) {
       ArtifactActive aa = new ArtifactActive(420);
       String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
       for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
         ItemStack confItem = this.m.getArtifact(key);
         ItemMeta confMeta = confItem.getItemMeta();
         confMeta.addEnchant(aa, 1, true);
         confItem.setItemMeta(confMeta);
         if (hit.getInventory().containsAtLeast(confItem, 1))
           for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
             if (effects.equalsIgnoreCase("BOUNCE")) {
               Random rand = new Random();
               int chance = SettingsFile.getSettings().getConfigurationSection("settings").getInt("bounceChance") - 1;
               int value = rand.nextInt(chance);
               if (value == 1) {
                 Vector v = target.getLocation().getDirection().multiply(-2);
                 v.setY(0);
                 target.setVelocity(v);
                 hit.sendMessage(prefix + "You knocked back " + target.getDisplayName());
                 target.sendMessage(prefix + "Enemies artifact knocked you back");
               }
             }
           }
       }
     }
   }
 }
