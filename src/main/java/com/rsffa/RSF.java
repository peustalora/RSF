package com.rsffa;

import com.rsffa.cmds.CheckWhile;
import com.rsffa.cmds.SetLocCmd;
import com.rsffa.listners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class RSF extends JavaPlugin {

    @Override
    public void onEnable() {

        // config.yml
        getConfig().options().copyDefaults();
        saveDefaultConfig();


        getCommand("ffaloc").setExecutor(new SetLocCmd(this));
        getCommand("checkwhile").setExecutor(new CheckWhile(this));
        getServer().getPluginManager().registerEvents(new BasicListners(this), this);
        getServer().getPluginManager().registerEvents(new OnPlaceEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnJoinEnterGame(this), this);
        getServer().getPluginManager().registerEvents(new DamageEvent(this), this);
        getServer().getPluginManager().registerEvents(new DamageEntityEvent(this), this);

    }

    @Override
    public void onDisable() {

    }
}
