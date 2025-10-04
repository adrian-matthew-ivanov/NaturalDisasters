package com.prygin.naturalDisasters;

import io.papermc.paper.command.brigadier.BasicCommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class NaturalDisasters extends JavaPlugin {

    public static String namespace = "naturaldisasters";

    public static int eventLength;

    @Override
    public void onEnable() {
        BasicCommand startNaturalEvent = new StartNaturalEvent();
        registerCommand("startevent", startNaturalEvent);

        registerCommand("seteventduration", new SetDisasterLength());
        registerCommand("geteventduration", new GetDisasterLength());

        Bukkit.getServer().getLogger().info("Natural Disasters Plugin Loaded");
    }

    @Override
    public void onLoad() {
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        eventLength = (int) config.get("eventduration");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        FileConfiguration config = this.getConfig();

        config.set("eventduration", eventLength);

        this.saveConfig();
    }
}
