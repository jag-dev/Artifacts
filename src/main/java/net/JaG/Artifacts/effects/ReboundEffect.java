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

 public class ReboundEffect implements Listener {
   Artifacts m;
   public ReboundEffect(Artifacts main) {
/* 20 */     this.m = main;
/*    */   }

   @EventHandler
   public void onUseRebound(EntityDamageByEntityEvent e) {
     if (!(e.getEntity() instanceof Player)) return;
     Player target = (Player)e.getEntity();
     if (!this.m.activeUsers.containsKey(target)) return;

     if (target.hasPermission("artifacts.*") || target.hasPermission("artifacts.effects") || target.hasPermission("artifacts.effect.rebound")) {
       Random rand = new Random();
       int chance = SettingsFile.getSettings().getConfigurationSection("settings").getInt("reboundChance");
       int value = rand.nextInt(chance);
       if (target.getHealth() <= 2.0D && (
         value == 1 || chance == 1)) {
         ArtifactActive aa = new ArtifactActive(420);
         String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
         for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
           ItemStack confItem = this.m.getArtifact(key);
           ItemMeta confMeta = confItem.getItemMeta();
           confMeta.addEnchant(aa, 1, true);
           confItem.setItemMeta(confMeta);
           if (target.getInventory().containsAtLeast(confItem, 1))
             for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
               if (effects.equalsIgnoreCase("REBOUND")) {
                 target.setHealth(10.0D);
                 target.sendMessage(prefix + "Rebound has activated");
               }
             }
         }
       }
     }
   }
 }