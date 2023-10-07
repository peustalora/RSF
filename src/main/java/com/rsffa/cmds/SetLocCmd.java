package com.rsffa.cmds;

import com.rsffa.RSF;
import com.rsffa.confManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import static org.bukkit.ChatColor.*;

public class SetLocCmd implements CommandExecutor {
    private final RSF plugin;

    public SetLocCmd(RSF plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            FileConfiguration config = plugin.getConfig();
            config.options().copyDefaults(true);
            confManager manager = new confManager(plugin);

            if (args.length < 1) {
                player.sendMessage("Wrong use of the command, please use:\n/ffaloc <spawn>\n/ffaloc <x/ymin/ymax/z> <number>");
                return true;
            }

            if (args[0].equalsIgnoreCase("spawn")) {
                if (plugin.getConfig().get("spawn") != null) {
                    player.sendMessage("Old locate: " + GOLD + player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ());
                }
                manager.setLocation(player.getLocation(), "spawn");
                plugin.saveConfig();

                player.sendMessage("Spawn locate set in: " + GOLD + manager.getLocation("spawn"));
            } else if (
                    args[0].equalsIgnoreCase("loc1") ||
                    args[0].equalsIgnoreCase("loc2")
            ) {
                manager.setLocation(player.getLocation(), "boundary." + args[0]);
                player.sendMessage("Set " + GOLD + args[0]);
            } else {
                player.sendMessage("Wrong use of the command, please use:\n/ffaloc <spawn>\n/ffaloc <x/y/z> <number>");
                return false;
            }
        }

        return false;
    }
}
