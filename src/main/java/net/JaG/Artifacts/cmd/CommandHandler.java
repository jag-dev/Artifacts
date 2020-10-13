 package net.JaG.Artifacts.cmd;
 
 import java.util.ArrayList;

 import net.JaG.Artifacts.Artifacts;
 import net.JaG.Artifacts.utils.SettingsFile;
 import net.md_5.bungee.api.ChatColor;
 import org.bukkit.Bukkit;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.Plugin;
 
 public class CommandHandler implements CommandExecutor {
   private Artifacts m;
   
   public CommandHandler(Artifacts main) {
     this.m = main;
   } public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
     String base_cmd = ChatColor.YELLOW + "/artifact ";
     String prefix = ChatColor.translateAlternateColorCodes('&', SettingsFile.getSettings().getConfigurationSection("settings").getString("prefix"));
     
     if (cmd.getName().equalsIgnoreCase("artifact"))
       if (sender.hasPermission("artifacts.admin") || sender.hasPermission("artifacts.*")) {
         if (args.length == 0) {
           sender.sendMessage(String.valueOf(prefix) + ChatColor.GRAY + "Version " + this.m.getDescription().getVersion());
           sender.sendMessage(" ");
          sender.sendMessage(String.valueOf(base_cmd) + "give" + ChatColor.GRAY + " Give certain artifact to player");
           sender.sendMessage(String.valueOf(base_cmd) + "list" + ChatColor.GRAY + " List current artifacts");
           sender.sendMessage(String.valueOf(base_cmd) + "disable" + ChatColor.GRAY + " Disable certain artifact");
           sender.sendMessage(String.valueOf(base_cmd) + "enable" + ChatColor.GRAY + " Enable certain artifact");
           sender.sendMessage(String.valueOf(base_cmd) + "reload" + ChatColor.GRAY + " Reload file configuration");
           sender.sendMessage(" ");
         } else if (args.length == 1) {
           
           if (args[0].equalsIgnoreCase("list")) {
             ArrayList<String> artifacts = new ArrayList<>();
             for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) artifacts.add(key); 
             sender.sendMessage(String.valueOf(prefix) + ChatColor.GRAY + "Current Artifacts");
             sender.sendMessage(ChatColor.GREEN + "" +  artifacts);
           } 
           
           if (args[0].equalsIgnoreCase("give")) {
             sender.sendMessage(String.valueOf(prefix) + "Please specify an artifact");
             ArrayList<String> artifacts = new ArrayList<>();
             for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
               artifacts.add(key);
             }
             sender.sendMessage(ChatColor.GREEN + "" + "" + ChatColor.ITALIC + "" +  artifacts);
           } 
           
           if (args[0].equalsIgnoreCase("enable")) sender.sendMessage(String.valueOf(prefix) + "Specify an artifact to enable (/artifact list)");
           
           if (args[0].equalsIgnoreCase("disable")) sender.sendMessage(String.valueOf(prefix) + "Specify an artifact to disable (/artifact list)");
           
           if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("ver")) sender.sendMessage(String.valueOf(prefix) + "Currently running v" + this.m.getDescription().getVersion() + " by " + this.m.getDescription().getAuthors());
           
           if (args[0].equalsIgnoreCase("reload")) {
             Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("Artifacts");
             pl.reloadConfig();
             SettingsFile.reloadSettings();
             SettingsFile.saveSettings();
             
             sender.sendMessage(String.valueOf(prefix) + "Reloaded configuration");
           } 
         } else if (args.length == 2) {
           
           if (args[0].equalsIgnoreCase("give")) sender.sendMessage(String.valueOf(prefix) + "Usage: /artifact give <artifact> <player>");
           
           if (args[0].equalsIgnoreCase("enable")) {
             for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
               if (args[1].equalsIgnoreCase(key)) {
                 this.m.getConfig().set(String.valueOf(key) + ".enabled", Boolean.valueOf(true));
                 this.m.saveConfig();
                 this.m.saveDefaultConfig();
                 sender.sendMessage(String.valueOf(prefix) + "Enabled " + key);
               } 
             } 
           }
           
           if (args[0].equalsIgnoreCase("disable")) {
             for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) {
               if (args[1].equalsIgnoreCase(key)) {
                 this.m.getConfig().set(String.valueOf(key) + ".enabled", Boolean.valueOf(false));
                 this.m.saveConfig();
                 this.m.saveDefaultConfig();
                 sender.sendMessage(String.valueOf(prefix) + "Disabled " + key);
               } 
             } 
           }
         } else if (args.length == 3) {
           
           if (args[0].equalsIgnoreCase("give")) {
             if (args[1].isEmpty()) { sender.sendMessage(String.valueOf(prefix) + "Usage: /artifact give <artifact> <player>"); }
             else { try {
                 Player target = Bukkit.getServer().getPlayerExact(args[2]);
                 boolean doesExist = false;
                 String giveArtifact = "";
                 ArrayList<String> currentArtifacts = new ArrayList<>();
                 for (String key : this.m.getConfig().getConfigurationSection("").getKeys(false)) currentArtifacts.add(key); 
                 for (String singleArtifact : currentArtifacts) {
                   if (args[1].equalsIgnoreCase(singleArtifact)) {
                     doesExist = true;
                     giveArtifact = singleArtifact;
                   } 
                 } 
                 
                 if (doesExist) this.m.giveArtifact(giveArtifact, target); 
               } catch (NullPointerException ex) {
                 sender.sendMessage(String.valueOf(prefix) + "User is not online or doesn't exist");
               }  }
           
           }
         } 
       } else {
         
         sender.sendMessage(String.valueOf(prefix) + "You do not have permission");
       }  
     return true;
   }
 }
