package com.rsffa.listners;

import com.rsffa.RSF;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OnPlaceEvent implements Listener {

    private final RSF plugin;

    public OnPlaceEvent(RSF plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if(player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        HashMap<Player, List<Block>> playerBlockMap = BlockManager.getPlayerBlockMap();
        List<Block> blockList = playerBlockMap.getOrDefault(player, new ArrayList<>());
        blockList.add(e.getBlock());
        playerBlockMap.put(player, blockList);
    }

    public static class BlockManager {
        private static HashMap<Player, List<Block>> playerBlockMap = new HashMap<>();

        public static HashMap<Player, List<Block>> getPlayerBlockMap() {
            return playerBlockMap;
        }
    }
}
