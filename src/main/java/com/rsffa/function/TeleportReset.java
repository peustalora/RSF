package com.rsffa.function;

import com.rsffa.RSF;
import com.rsffa.confManager;
import com.rsffa.listners.OnPlaceEvent;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class TeleportReset {

    private final RSF plugin;
    public TeleportReset(RSF plugin) {
        this.plugin = plugin;
    }


    public void ResetItemPlayer(Player player) {

        player.getInventory().clear();

        ItemStack stick = new ItemStack (Material.STICK, 1);
        ItemMeta testEnchantMeta = stick.getItemMeta();
        testEnchantMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
        stick.setItemMeta(testEnchantMeta);

        player.getInventory().addItem(stick);
        player.getInventory().addItem(new ItemStack(Material.SANDSTONE, 64));

    }

    public void DeathResetPlayer(Player player) {

        final confManager manager = new confManager(plugin);
        final Location spawn = manager.getLocation("spawn");

        player.teleport(spawn);
        player.getInventory().clear();
        player.setGameMode(GameMode.ADVENTURE);

        HashMap<Player, List<Block>> playerBlockMap = OnPlaceEvent.BlockManager.getPlayerBlockMap();
        List<Block> blockList = playerBlockMap.get(player);

        if (blockList != null) {
            for (Block block : blockList) {
                block.setType(Material.AIR);
                block.getState().update(true, true); // Update the block state to force the change
            }

            blockList.clear(); // Clear the block list
        }
    }

}
