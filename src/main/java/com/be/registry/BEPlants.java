package com.be.registry;

import com.be.utils.RegistryHandler;
import io.github.thebusybiscuit.exoticgarden.PlantType;
import org.bukkit.ChatColor;

public class BEPlants {

    public static void onPlantsRegister() {
        RegistryHandler.initPlant("BIG_CARROT", "大萝卜", ChatColor.RED, PlantType.DOUBLE_PLANT, "2448c183a7640867e42118e69c3f4d15db1ffb0d93646b77078ecedca2a43454");
        RegistryHandler.initPlant("BIG_WHEAT", "大麦", ChatColor.YELLOW, PlantType.DOUBLE_PLANT, "3b3c84e4bdaf5cc5f85632ac928d059fc2f1ff0cc9e5998f1fe8b227881ada85");
        RegistryHandler.initPlant("AMARANTH", "苋菜", ChatColor.RED, PlantType.FRUIT, "e6ef614b3a5fbec9b8af35d8a40e91dccdd8977c712ecef6ce52d91af49c4c93");
        RegistryHandler.initPlant("PISTACHIO", "开心果", ChatColor.DARK_GREEN, PlantType.DOUBLE_PLANT, "52a90a34d8740818b0bab2a687ebd2bfd956e08949d930d6ace666f470b3d9c8");
        RegistryHandler.initPlant("PEANUT", "花生", ChatColor.LIGHT_PURPLE, PlantType.FRUIT, "608043c5788050ce7ee54edddd48239bce491a9949d1410ad79e165436153ea4");
        RegistryHandler.initPlant("MEXICO_CHILI", "墨西哥辣椒", ChatColor.DARK_GREEN, PlantType.DOUBLE_PLANT, "5c8e453e84f663f2f6f4af8ed58e65a47aa8c5bffc2a4f67fad318a523b7a75c");
        RegistryHandler.initPlant("INFERNOFRUIT", "地狱果", ChatColor.DARK_RED, PlantType.DOUBLE_PLANT, "37faca995aa2bfa391f1c9ddcb20118fdc331bf5c8a5172bb4c7eb038e3d2b2c");
        RegistryHandler.initPlant("ACORN_SQUASH", "橡子南瓜", ChatColor.GRAY, PlantType.DOUBLE_PLANT, "bde904b116304c3e816b1b8c75c2184260f20591077f63be871bc71675092aa8");
        RegistryHandler.initPlant("ROSE", "玫瑰", ChatColor.DARK_RED, PlantType.DOUBLE_PLANT, "16fb9b3e3f650b7b258c04ffcb85c1b5dcac92b81e52d992c5124b670fe8d6");
        RegistryHandler.initPlant("SAKURA", "樱花", ChatColor.LIGHT_PURPLE, PlantType.DOUBLE_PLANT, "30a39da2c099f7277969184ca32a74a53aea5bc8b645fbdf5f31e5fbba75f844");
        RegistryHandler.initPlant("REED", "甘蔗", ChatColor.GREEN, PlantType.FRUIT, "8624bacb5f1986e6477abce4ae7dca1820a5260b6233b55ba1d9ba936c84b");
        RegistryHandler.initPlant("ALOE", "芦荟", ChatColor.GREEN, PlantType.DOUBLE_PLANT, "2e3c538caa4d6e3089ee36f69c958e1ab31859a27221e0cd6173030589f03473");
    }

}
