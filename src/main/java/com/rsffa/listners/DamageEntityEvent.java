package com.rsffa.listners;

import com.rsffa.RSF;
import com.rsffa.function.TeleportReset;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEntityEvent implements Listener {

    private final RSF plugin;
    private final TeleportReset teleportReset;

    public DamageEntityEvent(RSF plugin) {
        this.plugin = plugin;
        teleportReset = new TeleportReset(plugin);
    }

    @EventHandler
    public void EntityDamageByEntity(EntityDamageByEntityEvent e) {

        if(e.getEntity() instanceof Player) {

        Player player = (Player) e.getEntity();
        final boolean recievedamage = plugin.getConfig().getBoolean("canDamage");

        if(!recievedamage) {
            e.setDamage(0);
             return;
        }

        if(e.getDamage() >= ((Player) e.getEntity()).getHealth()) {
             e.setCancelled(true);
             player.setHealth(20);
             teleportReset.DeathResetPlayer(player);
             teleportReset.ResetItemPlayer(player);
        }

    }

}
}
