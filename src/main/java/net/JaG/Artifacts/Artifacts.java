 package net.JaG.Artifacts;
 
 import java.lang.reflect.Field;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;

 import net.JaG.Artifacts.cmd.CommandHandler;
 import net.JaG.Artifacts.effects.BlacksmithEffect;
 import net.JaG.Artifacts.effects.BounceEffect;
 import net.JaG.Artifacts.effects.DolphinEffect;
 import net.JaG.Artifacts.effects.EscapeEffect;
 import net.JaG.Artifacts.effects.ExpStealEffect;
 import net.JaG.Artifacts.effects.FatEffect;
 import net.JaG.Artifacts.effects.FeatherEffect;
 import net.JaG.Artifacts.effects.FriendEffect;
 import net.JaG.Artifacts.effects.HauntEffect;
 import net.JaG.Artifacts.effects.ImmuneEffect;
 import net.JaG.Artifacts.effects.MercyEffect;
 import net.JaG.Artifacts.effects.NinjaEffect;
 import net.JaG.Artifacts.effects.ReboundEffect;
 import net.JaG.Artifacts.effects.StarveEffect;
 import net.JaG.Artifacts.effects.StunEffect;
 import net.JaG.Artifacts.effects.VenomEffect;
 import net.JaG.Artifacts.listener.ArtifactListener;
 import net.JaG.Artifacts.listener.UsageListener;
 import net.JaG.Artifacts.utils.ArtifactActive;
 import net.JaG.Artifacts.utils.SettingsFile;
 import net.md_5.bungee.api.ChatColor;
 import org.bukkit.Bukkit;
 import org.bukkit.Material;
 import org.bukkit.enchantments.Enchantment;
 import org.bukkit.entity.Player;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.plugin.java.JavaPlugin;
 import org.bukkit.potion.PotionEffect;
 import org.bukkit.potion.PotionEffectType;
 /*
  * @author JaGv
  * @version 1.9
  */
 public class Artifacts extends JavaPlugin {
   Artifacts m;
   public HashMap<Player, Boolean> activeUsers = new HashMap<>();
   public void onEnable() {
     Bukkit.getServer().getPluginManager().registerEvents(new ArtifactListener(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents(new UsageListener(this), (Plugin)this);
     getCommand("artifact").setExecutor(new CommandHandler(this));
     saveDefaultConfig();
     SettingsFile.fileSetup();
     createDefaultSettings();
     SettingsFile.getSettings().options().copyDefaults(true);
     SettingsFile.saveSettings();
     registerArtifactActive();
     registerEffects();
   }
   
   public void registerArtifactActive() {
     try {
       Field f = Enchantment.class.getDeclaredField("acceptingNew");
       f.setAccessible(true);
       f.set(null, true);
     } catch (Exception e) {
       e.printStackTrace();
     }  
     try { ArtifactActive active = new ArtifactActive(420);
       Enchantment.registerEnchantment(active); }
     
     catch (IllegalArgumentException illegalArgumentException) {  }
     catch (Exception e) { e.printStackTrace(); }
   
   }
   public ItemStack getArtifact(String section) {
     Material mat = Material.getMaterial(getConfig().getConfigurationSection(section).getString(".id"));
     ItemStack artifact = new ItemStack(mat);
     ItemMeta artifactMeta = artifact.getItemMeta();
     artifactMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getConfigurationSection(section).getString(".name")));
     List<String> lore = getConfig().getConfigurationSection(section).getStringList(".desc");
     ArrayList<String> colorLore = new ArrayList<>();
     for (String line : lore) colorLore.add(ChatColor.translateAlternateColorCodes('&', line)); 
     colorLore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Right click to toggle");
     artifactMeta.setLore(colorLore);
     artifact.setItemMeta(artifactMeta);
     
     return artifact;
   }
   public void giveArtifact(String artifact, Player player) {
     ItemStack a = new ItemStack(Material.getMaterial(getConfig().getConfigurationSection(artifact).getString(".id").toUpperCase()));
     ItemMeta artifactMeta = a.getItemMeta();
     String name = getConfig().getConfigurationSection(artifact).getString(".name");
     List<String> lore = getConfig().getConfigurationSection(artifact).getStringList(".desc");
     ArrayList<String> colorLore = new ArrayList<>();
     for (String line : lore) colorLore.add(ChatColor.translateAlternateColorCodes('&', line)); 
     colorLore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Right click to toggle");
     artifactMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
     artifactMeta.setLore(colorLore);
     
     a.setItemMeta(artifactMeta);
     player.getInventory().addItem(a);
   }
   
   public void registerEffects() {
     Bukkit.getServer().getPluginManager().registerEvents(new NinjaEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new BounceEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new ReboundEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new FriendEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new HauntEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new DolphinEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new ImmuneEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new EscapeEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new MercyEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new FeatherEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new FatEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new StunEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new StarveEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new BlacksmithEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new VenomEffect(this), this);
     Bukkit.getServer().getPluginManager().registerEvents(new ExpStealEffect(this), this);
   }
   
   public static void createDefaultSettings() {
     SettingsFile.getSettings().addDefault("settings.prefix", "&cArtifacts &8&l>&7 ");
     SettingsFile.getSettings().addDefault("settings.bounceChance", 20);
     SettingsFile.getSettings().addDefault("settings.hauntChance", 25);
     SettingsFile.getSettings().addDefault("settings.reboundChance", 10);
     SettingsFile.getSettings().addDefault("settings.ninjaSeconds", 3);
     SettingsFile.getSettings().addDefault("settings.escapeChance", 15);
     SettingsFile.getSettings().addDefault("settings.escapeSpeedDuration", 5);
     SettingsFile.getSettings().addDefault("settings.mercyChance", 20);
     SettingsFile.getSettings().addDefault("settings.mercyDuration", 5);
     SettingsFile.getSettings().addDefault("settings.stunChance", 20);
     SettingsFile.getSettings().addDefault("settings.stunDuration", 3);
     SettingsFile.getSettings().addDefault("settings.starveChance", 20);
     SettingsFile.getSettings().addDefault("settings.starveLevel", 1);
     SettingsFile.getSettings().addDefault("settings.starveDuration", 3);
     SettingsFile.getSettings().addDefault("settings.venomChance", 20);
     SettingsFile.getSettings().addDefault("settings.venomLevel", 1);
     SettingsFile.getSettings().addDefault("settings.venomDuration", 10);
     SettingsFile.getSettings().addDefault("settings.expStealChance", 20);
   }


	public void removeEffects(String section, Player p) {
		List<String> effects = this.getConfig().getConfigurationSection(section).getStringList(".potion_effects");
		for (String key : effects) {
			switch (key.toUpperCase()) {
			case "SPEED:2":
				p.removePotionEffect(PotionEffectType.SPEED);
				break;
			case "REGENERATION:2":
				p.removePotionEffect(PotionEffectType.REGENERATION);
				break;
			case "POISON:2":
				p.removePotionEffect(PotionEffectType.POISON);
				break;
			case "INCREASE_DAMAGE:2":
				p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				break;
			case "JUMP:2":
				p.removePotionEffect(PotionEffectType.JUMP);
				break;
			case "SPEED:3":
				p.removePotionEffect(PotionEffectType.SPEED);
				break;
			case "REGENERATION:3":
				p.removePotionEffect(PotionEffectType.REGENERATION);
				break;
			case "POISON:3":
				p.removePotionEffect(PotionEffectType.POISON);
				break;
			case "INCREASE_DAMAGE:3":
				p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				break;
			case "JUMP:3":
				p.removePotionEffect(PotionEffectType.JUMP);
				break;
			default:
				try { p.removePotionEffect(PotionEffectType.getByName(key.toUpperCase())); }
				catch (NullPointerException e) { System.out.println("[WARNING] Effect type " + key + " is not recognized"); }
				break;
			}
		}
	}
	
	public void giveEffects(String section, Player p) {
		List<String> effects = this.getConfig().getConfigurationSection(section).getStringList(".potion_effects");
		for (String key : effects) {
			switch (key.toUpperCase()) {
			case "SPEED:2":
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
				break;
			case "REGENERATION:2":
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1));
				break;
			case "POISON:2":
				p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, 1));
				break;
			case "INCREASE_DAMAGE:2":
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
				break;
			case "JUMP:2":
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));
				break;
			case "SPEED:3":
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
				break;
			case "REGENERATION:3":
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 2));
				break;
			case "POISON:3":
				p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, 2));
				break;
			case "INCREASE_DAMAGE:3":
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2));
				break;
			case "JUMP:3":
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2));
				break;
			default:
				try { p.addPotionEffect(new PotionEffect(PotionEffectType.getByName(key), Integer.MAX_VALUE, 0)); }
				catch (NullPointerException e) { System.out.println("[WARNING] Effect type " + key + " is not recognized"); }
				break;
			}
		}
	}
	
	public void toggleActiveItems(Player p) {
		if (this.activeUsers.containsKey(p)) {
			if (checkForActiveItems(p)) return;  
			this.activeUsers.remove(p);
     } else {
       this.activeUsers.put(p, true);
     } 
   }
	
   public boolean checkForActiveItems(Player p) {
     boolean val = false;
    ArtifactActive aa = new ArtifactActive(420); byte b; int i; ItemStack[] arrayOfItemStack;
    for (i = (arrayOfItemStack = p.getInventory().getContents()).length, b = 0; b < i; ) { ItemStack item = arrayOfItemStack[b];
       if (item != null && 
         item.containsEnchantment(aa)) val = true;  b++; }
     
    if (val) return true; 
     return false;
   }
  
   public String checkArtifactItemActive(ItemStack checkItem, int amount) {
     String value = "none";
     for (String key : getConfig().getConfigurationSection("").getKeys(false)) {
       ItemStack confArtifact = getArtifact(key);
       ItemMeta confMeta = confArtifact.getItemMeta();
       ArtifactActive aa = new ArtifactActive(420);
      confMeta.addEnchant(aa, 1, true);
       confArtifact.setItemMeta(confMeta);
       confArtifact.setAmount(amount);
       if (checkItem.equals(confArtifact)) value = key; 
     } 
     return value;
   }
 }