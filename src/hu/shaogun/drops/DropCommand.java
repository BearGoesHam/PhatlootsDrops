package hu.shaogun.drops;

import com.codisimus.plugins.phatloots.*;
import com.codisimus.plugins.phatloots.events.PhatLootChestEvent;
import com.codisimus.plugins.phatloots.loot.Loot;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DropCommand implements Listener, CommandExecutor {


    ArrayList<Double> X = new ArrayList<Double>();
    ArrayList<Double> Y = new ArrayList<Double>();
    ArrayList<Double> Z = new ArrayList<Double>();
    ArrayList<PhatLoot> loot = new ArrayList<PhatLoot>();




    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // /lootdrop <chest/item> <world> <x> <y> <z> <nameofloot>



        String name;

        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("lootdrop")) {
            if (sender == null) {
                p.sendMessage("Nem használhatja ezt!");
                return true;
            } else if (args[0].equalsIgnoreCase("chest")) {

                if (args.length < 6) {
                    p.sendMessage(ChatColor.RED + "Error");
                    return true;
                } else {
                    if (args.length == 6) {
                        World world = Bukkit.getWorld(args[1]);
                        double x = Double.parseDouble(args[2]);
                        double y = Double.parseDouble(args[3]);
                        double z = Double.parseDouble(args[4]);
                        if (world == null) {
                            p.sendMessage(ChatColor.RED + "Invalid World!");
                            return true;
                        } else {


                        if (!PhatLoots.hasPhatLoot(args[5])) {
                            p.sendMessage(ChatColor.RED + "Invalid PhatLoot!");
                            return true;
                        } else {
                            Location chestLoc = new Location(world, x, y, z);
                            chestLoc.getBlock().setType(Material.CHEST);
                            Chest chest = (Chest) chestLoc.getBlock().getState();
                            Inventory inv = chest.getInventory();
                            //ItemStack ds = new ItemStack(Material.DIAMOND_SWORD, 1);
                            //ItemMeta dm = ds.getItemMeta();
                            //dm.setDisplayName("Example");
                            //ds.setItemMeta(dm);
                            //inv.setItem(26, ds);
                            PhatLootsAPI.getLinkedPhatLoots(chest.getBlock());
                            PhatLootChest.getChest(chest.getBlock());
                            p.sendMessage(ChatColor.RED + "Drop located at: " + x + ", " + y + ", " + "," + z + " with loot: " + args[5]);
                            PhatLoot loot = PhatLoots.getPhatLoot(args[5]);


                            PhatLoots.addPhatLoot(loot);


                            PhatLootChest chest1 = PhatLootChest.getChest(chest.getBlock());
                            chest1.getLinkedPhatLoots().add(loot);


                        }
                    }
                    }
                }

            } else if(args[0].equalsIgnoreCase("item")) {
                if(args.length < 6) {
                }
            }
                if (args.length == 6) {
                    World world = Bukkit.getWorld(args[1]);
                    double x = Double.parseDouble(args[2]);
                    double y = Double.parseDouble(args[3]);
                    double z = Double.parseDouble(args[4]);
                    if (!PhatLoots.hasPhatLoot(args[5])) {
                        p.sendMessage(ChatColor.RED + "Invalid PhatLoot!");
                        return true;
                    }



                    //world.dropItem(x, y, z, Material.SUGAR);

            }
        }
        return false;

    }
}
