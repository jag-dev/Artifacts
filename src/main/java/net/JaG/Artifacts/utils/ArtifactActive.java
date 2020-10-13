 package net.JaG.Artifacts.utils;
 import org.bukkit.enchantments.Enchantment;
 import org.bukkit.enchantments.EnchantmentTarget;
 import org.bukkit.inventory.ItemStack;
 
 public class ArtifactActive extends Enchantment {
   public ArtifactActive(int id) {
     super(id);
   }
   public boolean canEnchantItem(ItemStack arg0) {
     return false;
   }
   public boolean conflictsWith(Enchantment arg0) {
     return false;
   }
   public EnchantmentTarget getItemTarget() {
     return null;
   }
   public int getMaxLevel() {
     return 0;
   }
   public String getName() {
     return "Active";
   }
   public int getStartLevel() {
     return 0;
   }
 }
