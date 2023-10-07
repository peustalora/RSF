package com.rsffa.listners;

import com.rsffa.RSF;
import com.rsffa.cmds.CheckWhile;
import com.rsffa.confManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinEnterGame implements Listener {


    private final RSF plugin;
    private final CheckWhile checkWhile;



    public OnJoinEnterGame(RSF plugin) {
        this.plugin = plugin;
        checkWhile = new CheckWhile(plugin);
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        final confManager manager = new confManager(plugin);
        final Location spawn = manager.getLocation("spawn");

        if(spawn == null || manager.getLocation("boundary.loc1") == null) {
            plugin.getLogger().info("Please config correctly the plugin before starting a game");
            return;
        }

        Player player = e.getPlayer();

        player.teleport(spawn);
        player.setGameMode(GameMode.ADVENTURE);
        checkWhile.startWhileLoop(player);


    }

}
