package com.be.utils;

import com.be.BEPlugin;
import com.be.fixes.HeadDropFix;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
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

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        BEPlugin.getInstance().reloadConfig();
        if (BEPlugin.getInstance().getConfig().getString("spawn.world") != null && BEPlugin.getInstance().getConfig().getString("spawn.x") != null && BEPlugin.getInstance().getConfig().getString("spawn.y") != null && BEPlugin.getInstance().getConfig().getString("spawn.z") != null && BEPlugin.getInstance().getConfig().getString("spawn.yaw") != null && BEPlugin.getInstance().getConfig().getString("spawn.pitch") != null) {
            World world = Bukkit.getWorld(BEPlugin.getInstance().getConfig().getString("spawn.world"));
            double x = BEPlugin.getInstance().getConfig().getDouble("spawn.x");
            double y = BEPlugin.getInstance().getConfig().getDouble("spawn.y");
            double z = BEPlugin.getInstance().getConfig().getDouble("spawn.z");
            float yaw = (float) BEPlugin.getInstance().getConfig().getDouble("spawn.yaw");
            float pitch = (float) BEPlugin.getInstance().getConfig().getDouble("spawn.pitch");

            Location loc = new Location(world, x, y, z, yaw, pitch);
            event.getPlayer().teleportAsync(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);

        }
    }
}
