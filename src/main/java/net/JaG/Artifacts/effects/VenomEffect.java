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
 import org.bukkit.potion.PotionEffect;
 import org.bukkit.potion.PotionEffectType;

 public class VenomEffect implements Listener {
   Artifacts m;
   public VenomEffect(Artifacts main) {
/* 21 */     this.m = main;
/*    */   }

   @EventHandler
   public void onVenom(EntityDamageByEntityEvent e) {
     if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
     Player hit = (Player)e.getEntity();
     if (!this.m.activeUsers.containsKey(hit)) return;

     if (hit.hasPermission("artifacts.*") || hit.hasPermission("artifacts.effects") || hit.hasPermission("artifacts.effect.venom")) {
       String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
       for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
         ItemStack confItem = this.m.getArtifact(key);
         ItemMeta confMeta = confItem.getItemMeta();
         ArtifactActive aa = new ArtifactActive(420);
         confMeta.addEnchant(aa, 1, true);
         confItem.setItemMeta(confMeta);
         if (hit.getInventory().containsAtLeast(confItem, 1))
           for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
             if (effects.equalsIgnoreCase("VENOM")) {
               Random rand = new Random();
               int chance = SettingsFile.getSettings().getConfigurationSection("settings").getInt("venomChance");
               int level = SettingsFile.getSettings().getConfigurationSection("settings").getInt("venomLevel") - 1;
               int duration = SettingsFile.getSettings().getConfigurationSection("settings").getInt("venomDuration");
               int value = rand.nextInt(chance);
               if (value == 1 || chance == 1) {
                 Player dmger = (Player)e.getDamager();
                 dmger.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration * 20, level));
                 dmger.sendMessage(prefix + "The enemy has poisoned you");
               }
             }
           }
       }
     }
   }
 }