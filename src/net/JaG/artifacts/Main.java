 package net.JaG.artifacts;
 
 import java.lang.reflect.Field;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import net.JaG.artifacts.effects.BlacksmithEffect;
 import net.JaG.artifacts.effects.BounceEffect;
 import net.JaG.artifacts.effects.DolphinEffect;
 import net.JaG.artifacts.effects.EscapeEffect;
 import net.JaG.artifacts.effects.ExpStealEffect;
 import net.JaG.artifacts.effects.FatEffect;
 import net.JaG.artifacts.effects.FeatherEffect;
 import net.JaG.artifacts.effects.FriendEffect;
 import net.JaG.artifacts.effects.HauntEffect;
 import net.JaG.artifacts.effects.ImmuneEffect;
 import net.JaG.artifacts.effects.MercyEffect;
 import net.JaG.artifacts.effects.NinjaEffect;
 import net.JaG.artifacts.effects.ReboundEffect;
 import net.JaG.artifacts.effects.StarveEffect;
 import net.JaG.artifacts.effects.StunEffect;
 import net.JaG.artifacts.effects.VenomEffect;
 import net.md_5.bungee.api.ChatColor;
 import org.bukkit.Bukkit;
 import org.bukkit.Material;
 import org.bukkit.enchantments.Enchantment;
 import org.bukkit.entity.Player;
 import org.bukkit.event.Listener;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.plugin.java.JavaPlugin;
 import org.bukkit.potion.PotionEffect;
 import org.bukkit.potion.PotionEffectType;
 
 public class Main extends JavaPlugin {
   Main m;
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
       f.set(null, Boolean.valueOf(true));
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
     colorLore.add(ChatColor.YELLOW + "Middle click to activate");
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
     colorLore.add(ChatColor.YELLOW + "Middle click to activate");
     artifactMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
     artifactMeta.setLore(colorLore);
     
     a.setItemMeta(artifactMeta);
     player.getInventory().addItem(new ItemStack[] { a });
   }
   
   public void registerEffects() {
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new NinjaEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new BounceEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new ReboundEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new FriendEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new HauntEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new DolphinEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new ImmuneEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new EscapeEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new MercyEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new FeatherEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new FatEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new StunEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new StarveEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new BlacksmithEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new VenomEffect(this), (Plugin)this);
     Bukkit.getServer().getPluginManager().registerEvents((Listener)new ExpStealEffect(this), (Plugin)this);
   }
   
   public static void createDefaultSettings() {
     SettingsFile.getSettings().addDefault("settings.prefix", "&cArtifacts &8&l>&7 ");
     SettingsFile.getSettings().addDefault("settings.bounceChance", Integer.valueOf(20));
     SettingsFile.getSettings().addDefault("settings.hauntChance", Integer.valueOf(25));
     SettingsFile.getSettings().addDefault("settings.reboundChance", Integer.valueOf(10));
     SettingsFile.getSettings().addDefault("settings.ninjaSeconds", Integer.valueOf(3));
     SettingsFile.getSettings().addDefault("settings.escapeChance", Integer.valueOf(15));
     SettingsFile.getSettings().addDefault("settings.escapeSpeedDuration", Integer.valueOf(5));
     SettingsFile.getSettings().addDefault("settings.mercyChance", Integer.valueOf(20));
     SettingsFile.getSettings().addDefault("settings.mercyDuration", Integer.valueOf(5));
     SettingsFile.getSettings().addDefault("settings.stunChance", Integer.valueOf(20));
     SettingsFile.getSettings().addDefault("settings.stunDuration", Integer.valueOf(3));
     SettingsFile.getSettings().addDefault("settings.starveChance", Integer.valueOf(20));
     SettingsFile.getSettings().addDefault("settings.starveLevel", Integer.valueOf(1));
     SettingsFile.getSettings().addDefault("settings.starveDuration", Integer.valueOf(3));
     SettingsFile.getSettings().addDefault("settings.venomChance", Integer.valueOf(20));
     SettingsFile.getSettings().addDefault("settings.venomLevel", Integer.valueOf(1));
     SettingsFile.getSettings().addDefault("settings.venomDuration", Integer.valueOf(10));
     SettingsFile.getSettings().addDefault("settings.expStealChance", Integer.valueOf(20));
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
       this.activeUsers.put(p, Boolean.valueOf(true));
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