 package net.JaG.Artifacts.effects;

 import net.JaG.Artifacts.utils.ArtifactActive;
 import net.JaG.Artifacts.Artifacts;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.player.PlayerItemConsumeEvent;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 import org.bukkit.potion.PotionEffect;
 import org.bukkit.potion.PotionEffectType;

 public class FatEffect implements Listener {
   Artifacts m;
   public FatEffect(Artifacts main) {
     this.m = main;
   }
   @EventHandler
   public void onFat(PlayerItemConsumeEvent e) {
     Player p = e.getPlayer();
     if (!this.m.activeUsers.containsKey(p)) return;
     if (p.hasPermission("artifacts.*") || p.hasPermission("artifacts.effects") || p.hasPermission("artifacts.effect.fat"))
       for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
         ItemStack confItem = this.m.getArtifact(key);
         ItemMeta confMeta = confItem.getItemMeta();
         ArtifactActive aa = new ArtifactActive(420);
         confMeta.addEnchant(aa, 1, true);
         confItem.setItemMeta(confMeta);
         if (p.getInventory().containsAtLeast(confItem, 1))
           for (String effects : this.m.getConfig().getConfigurationSection(key).getStringList("custom_effects")) {
             if (effects.equalsIgnoreCase("FAT"))
               p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 6000, 0));
           }
       }
   }
 }