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

 public class ExpStealEffect implements Listener {
   Artifacts m;
   public ExpStealEffect(Artifacts main) {
/* 19 */     this.m = main;
/*    */   }

   @EventHandler
   public void onSteal(EntityDamageByEntityEvent e) {
     if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
     Player dmgr = (Player)e.getDamager();
     if (!this.m.activeUsers.containsKey(dmgr)) return;
     if (dmgr.hasPermission("artifacts.*") || dmgr.hasPermission("artifacts.effects") || dmgr.hasPermission("artifacts.effect.expsteal")) {
       String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
       for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
         ItemStack confItem = this.m.getArtifact(key);
         ItemMeta confMeta = confItem.getItemMeta();
         ArtifactActive aa = new ArtifactActive(420);
         confMeta.addEnchant(aa, 1, true);
         confItem.setItemMeta(confMeta);
         if (dmgr.getInventory().containsAtLeast(confItem, 1))
           for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
             if (effects.equalsIgnoreCase("EXP_STEAL")) {
               Random rand = new Random();
               int chance = SettingsFile.getSettings().getConfigurationSection("settings").getInt("expStealChance");
               int value = rand.nextInt(chance);
               if (value == 1 || chance == 1) {
                 Player enemy = (Player)e.getEntity();
                 int enemy_exp = enemy.getTotalExperience();
                 dmgr.giveExp(enemy_exp);
                 enemy.setExp(0.0F);
                 enemy.setLevel(0);
                 dmgr.sendMessage(prefix + "Stole enemies experience");
               }
             }
           }
       }
     }
   }
 }