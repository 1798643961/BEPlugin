package com.be.fixes;

import com.be.BEPlugin;
import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadDropFix {

    public static boolean dropSkull = false;
    public static boolean scanChunk = true;
    public static boolean scanItemFrame = false;

    public static void onHeadDropFix() {
        HeadDropFix.dropSkull = BEPlugin.getInstance().getConfig().getBoolean("fixes.drop-skull");
        HeadDropFix.scanItemFrame = BEPlugin.getInstance().getConfig().getBoolean("fixes.scan-itemframe");
        HeadDropFix.scanChunk = BEPlugin.getInstance().getConfig().getBoolean("fixes.scan-chunk",true);
        Bukkit.getWorlds().forEach((world -> {
            if (scanItemFrame) {
                for (Entity entity : world.getEntities()) {
                    checkSkullInItemFrame(entity);
                }
            }
            if(scanChunk){
                for (Chunk chunk : world.getLoadedChunks()) {
                    for (BlockState state : chunk.getTileEntities()) {
                        checkAndRemoveSkull(state);
                    }
                }
            }
        }));
    }

    public static void checkSkullInItemFrame(Entity entity) {
        if (entity.getType() == EntityType.ITEM_FRAME) {
            ItemFrame itemFrame = (ItemFrame) entity;
            ItemStack stack = itemFrame.getItem();
            if (stack.getType() != Material.PLAYER_HEAD || stack.getType() != Material.PLAYER_WALL_HEAD) {
                SkullMeta skull = (SkullMeta) itemFrame.getItem().getItemMeta();
                if (skull.getPlayerProfile() != null) {
                    PlayerProfile playerProfile = skull.getPlayerProfile();
                    if (!playerProfile.hasTextures()) {
                        itemFrame.setItem(new ItemStack(Material.AIR));
                    }
                }
            }
        }
    }

    public static void checkAndRemoveSkull(BlockState state) {
        Material blockType = state.getType();
        if (blockType == Material.PLAYER_HEAD || blockType == Material.PLAYER_WALL_HEAD) {
            Skull skull = (Skull) state;
            if (skull.getPlayerProfile() != null) {
                PlayerProfile playerProfile = skull.getPlayerProfile();
                if (!playerProfile.hasTextures()) {
                    skull.getBlock().setType(Material.AIR);
                }
            }
        }
    }
}
