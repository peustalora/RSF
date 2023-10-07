package com.rsffa.listners;

import com.rsffa.RSF;
import com.rsffa.confManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BasicListners implements Listener {

    private final RSF plugin;
    public BasicListners(RSF plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        confManager manager = new confManager(plugin);
        Location location = manager.getLocation("spawn");

        if(location == null) {
            plugin.getLogger().info("No location available, not teleporting.");
            return;
        }

        e.getPlayer().teleport(location);

    }

}
