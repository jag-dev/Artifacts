 package net.JaG.Artifacts.effects;

 import net.JaG.Artifacts.utils.ArtifactActive;
 import net.JaG.Artifacts.Artifacts;
 import net.JaG.Artifacts.utils.SettingsFile;
 import net.md_5.bungee.api.ChatColor;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.player.PlayerToggleSneakEvent;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 import org.bukkit.potion.PotionEffect;
 import org.bukkit.potion.PotionEffectType;
 import org.bukkit.scheduler.BukkitRunnable;

 public class NinjaEffect implements Listener {
   Artifacts m;
   public NinjaEffect(Artifacts main) {
/* 21 */     this.m = main;
/*    */   }
   @EventHandler
   public void onUseNinja(PlayerToggleSneakEvent e) {
     final Player p = e.getPlayer();
     if (!this.m.activeUsers.containsKey(p)) return;

     if (p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.ninja")) {
       final PotionEffect camo = new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, 1);
       if (!p.isSneaking()) {
         final String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
         for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
           ItemStack confItem = this.m.getArtifact(key);
           ItemMeta confMeta = confItem.getItemMeta();
           ArtifactActive aa = new ArtifactActive(420);
           confMeta.addEnchant(aa, 1, true);
           confItem.setItemMeta(confMeta);
           if (p.getInventory().containsAtLeast(confItem, 1)) {
            for (String effect : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
               if (effect.equalsIgnoreCase("NINJA")) {
                 (new BukkitRunnable() {
                     int timer = SettingsFile.getSettings().getConfigurationSection("settings").getInt("ninjaSeconds") - 1;
                     public void run() {
                       if (this.timer >= 0) {
                         if (p.isSneaking()) {
                           p.sendMessage(prefix + "Invisibility enabling in: " + (this.timer + 1));
                           this.timer--;
                         } else if (!p.isSneaking()) {
                           p.sendMessage(prefix + "Please remain sneaking to gain effect");
                           cancel();
                         }
                       } else {

                         if (p.isSneaking()) {
                           p.addPotionEffect(camo);
                           p.sendMessage(prefix + "You are now invisible");
                         }
                         cancel();
                       }
                     }
                   }).runTaskTimer(this.m, 0L, 20L);
               }
             }

           }
         }
       } else if (p.isSneaking()) {

         final String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
         for (PotionEffect effect : p.getActivePotionEffects()) {
           if (effect.getType().equals(PotionEffectType.INVISIBILITY) && effect.getDuration() >= 1000000000) {
             p.removePotionEffect(PotionEffectType.INVISIBILITY);
             p.sendMessage(prefix + "Invisibility has been removed");
           }
         }
       }
     }
   }
 }