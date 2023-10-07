package com.rsffa.listners;

import com.rsffa.RSF;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;


public class DamageEvent implements Listener {


    private final RSF plugin;

    public DamageEvent(RSF plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent e) {
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            e.setCancelled(true);
        }

    }

}
