package com.rsffa.cmds;

import com.rsffa.RSF;
import com.rsffa.confManager;
import com.rsffa.function.Cuboid;
import com.rsffa.function.TeleportReset;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckWhile implements CommandExecutor {

    private final RSF plugin;
    private final TeleportReset teleportReset;
    private BukkitRunnable whileTask;



    public CheckWhile(RSF plugin) {
        this.plugin = plugin;
        teleportReset = new TeleportReset(plugin);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            FileConfiguration config = plugin.getConfig();
            config.options().copyDefaults(true);

            this.startWhileLoop(player);


        }
        return false;
    }

    public void startWhileLoop(final Player player) {

        this.stopWhileLoop();
        this.whileTask = new BukkitRunnable() {
            final confManager manager = new confManager(plugin);
            final Location loc1 = manager.getLocation("boundary.loc1");
            final Location loc2 = manager.getLocation("boundary.loc2");

            final Cuboid cube = new Cuboid(loc1, loc2);

            public void run() {
                if (!cube.isIn(player) && player.getGameMode() == GameMode.SURVIVAL) {

                    player.sendMessage("Você morreu.");
                    teleportReset.DeathResetPlayer(player);
                }

                if (player.getLocation().getBlockY() < cube.getMaxYCoordinate() && player.getGameMode() == GameMode.ADVENTURE) {
                    teleportReset.ResetItemPlayer(player);
                    player.setGameMode(GameMode.SURVIVAL);
                }
            }
        };
        this.whileTask.runTaskTimer(this.plugin, 0L, 5L);
    }

    private void stopWhileLoop() {
        if (this.whileTask != null) {
            this.whileTask.cancel();
            this.whileTask = null;
        }

    }


}
