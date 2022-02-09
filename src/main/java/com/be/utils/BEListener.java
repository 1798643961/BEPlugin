package com.be.utils;

import com.be.fixes.HeadDropFix;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class BEListener implements Listener {

    private static BEListener INSTANCE = new BEListener();

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void playerDeath(PlayerDeathEvent e) {
        if (!HeadDropFix.dropSkull) {
            return;
        }
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (!e.getEntity().getPlayerProfile().hasTextures()) {
            return;
        }
        ItemMeta meta = head.getItemMeta();
        SkullMeta skullMeta = (SkullMeta) meta;
        skullMeta.setPlayerProfile(e.getEntity().getPlayerProfile());
        head.setItemMeta(skullMeta);
        e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(), head);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onChunkLoad(ChunkLoadEvent event) {
        if(HeadDropFix.scanChunk) {
            for (BlockState state : event.getChunk().getTileEntities()) {
                HeadDropFix.checkAndRemoveSkull(state);
            }
        }
        if (HeadDropFix.scanItemFrame) {
            for (Entity entity : event.getChunk().getEntities()) {
                HeadDropFix.checkSkullInItemFrame(entity);
            }
        }
    }

    public static BEListener getInstance() {
        return INSTANCE;
    }
}
