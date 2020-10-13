 package net.JaG.Artifacts.utils;
 
 import java.io.File;
 import java.io.IOException;
 import org.bukkit.Bukkit;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.configuration.file.YamlConfiguration;
 
 public class SettingsFile
 {
   private static File file;
   private static FileConfiguration settingsFile;
   
   public static void fileSetup() {
     file = new File(Bukkit.getServer().getPluginManager().getPlugin("Artifacts").getDataFolder(), "settings.yml");
     if (!file.exists())
       
       try { file.createNewFile();
         System.out.println("[Artifacts] Created settings.yml"); }
       catch (IOException e) { System.out.println("[Artifacts] Could not create settings.yml"); }
        
     settingsFile = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
   }
   public static FileConfiguration getSettings() {
     return settingsFile;
   }
   public static void saveSettings() {
     
     try { settingsFile.save(file);
       System.out.println("[Artifacts] Saved settings.yml"); }
     catch (IOException e) { System.out.println("[Artifacts] Could not save settings.yml"); }
   
   }
   public static void reloadSettings() {
     settingsFile = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
     System.out.println("[Artifacts] Reloaded settings.yml");
   }
 }