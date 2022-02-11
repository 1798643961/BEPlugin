package com.be.utils;

import com.be.BEPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BEListener implements Listener {

    private static BEListener INSTANCE = new BEListener();

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


    @EventHandler
    public void onPerLogin(PlayerLoginEvent login) {
        Player p = login.getPlayer();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + p.getName() + "已登录");
        login.allow();
    }
}
