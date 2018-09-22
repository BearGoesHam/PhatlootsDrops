package hu.shaogun.drops;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        getCommand("lootdrop").setExecutor(new DropCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new DropCommand(), this);

    }

    public void onDisable() {

    }


}
