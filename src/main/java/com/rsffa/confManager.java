package com.rsffa;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class confManager {
    private final JavaPlugin plugin;
    private final FileConfiguration config;

    public confManager (JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        plugin.saveConfig();
    }

    public Location setLocation(Location location, String name) {
        ConfigurationSection nameSection = this.config.createSection(name);
        nameSection.set("world", location.getWorld().getName());
        nameSection.set("x", location.getX());
        nameSection.set("y", location.getY());
        nameSection.set("z", location.getZ());
        nameSection.set("yaw", Math.round(location.getYaw()));
        nameSection.set("pitch", Math.round(location.getPitch()));
        this.plugin.saveConfig();
        return location;
    }

    public Location getLocation(String name) {
        ConfigurationSection nameSection = this.config.getConfigurationSection(name);
        if (nameSection == null) {
            return null;
        } else {
            World world = Bukkit.getWorld(nameSection.getString("world"));
            double x = nameSection.getDouble("x");
            double y = nameSection.getDouble("y");
            double z = nameSection.getDouble("z");
            float yaw = (float)nameSection.getDouble("yaw");
            float pitch = (float)nameSection.getDouble("pitch");
            return new Location(world, x, y, z, yaw, pitch);
        }
    }
}
