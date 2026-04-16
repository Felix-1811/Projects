package dev.felix;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class MainPlugin extends JavaPlugin {

    public static MainPlugin INSTANCE;
    public static String PREFIX;


    @Override
    public void onEnable() {
        INSTANCE = this;
        PREFIX = "§1Plugin §7";
        getLogger().info("Plugin enabled! #1");
        registerRecipes();
        Bukkit.getConsoleSender().sendMessage(PREFIX + "Plugin enabled! #2");
    }

    public void registerRecipes(){
        ItemStack result = new ItemStack(Material.DIAMOND);
        NamespacedKey key = new NamespacedKey(this, "Gold_Zu_Diamond");
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("AAA"
                ,    "BBB",
                     "AAA");
        recipe.setIngredient('A', Material.IRON_INGOT);
        recipe.setIngredient('B', Material.GOLD_INGOT);
        Bukkit.addRecipe(recipe);
        getLogger().info(PREFIX + "Recipe registered");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Hallo command
        if (command.getName().equalsIgnoreCase("hallo")) {
            sender.sendMessage(PREFIX + "Hallo!");
            return true;
        }

        //Particle
        if(command.getName().equalsIgnoreCase("aura")) {
            Player player = (Player) sender;
            for(double i = 0; i < Math.PI; i++){
                double x  = Math.cos(i) * 1.5; //1.5 -> Radius
                double z = Math.sin(i) * 1.5;

                player.getWorld().spawnParticle(
                        Particle.HEART,
                        player.getLocation().add(x, 1, z), // Position// Partikel-Typ
                        1                                   // Anzahl
                );
                return true;

            }

        }

        return false;
    }


    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
        Bukkit.getConsoleSender().sendMessage(PREFIX + "Plugin disabled! #2 ");
    }
}