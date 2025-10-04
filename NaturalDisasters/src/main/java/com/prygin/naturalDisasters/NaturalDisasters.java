package com.prygin.naturalDisasters;

import com.prygin.naturalDisasters.MeteorShower.MeteorShower;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class NaturalDisasters extends JavaPlugin {

    public static String namespace = "naturaldisasters";

    public static int eventLength;

    public static Map<String, Function<CommandSourceStack, Boolean>> valid_events = new HashMap<>();

    public static Map<String, Class<?>> events = new HashMap<>();

    @Override
    public void onEnable() {
        valid_events.put(NaturalDisasters.namespace + ":meteor_shower", (CommandSourceStack source) -> {
            CommandSender sender = source.getSender();
            sender.sendMessage("Summoning a meteor shower.");
            MeteorShower event = new MeteorShower((Player) sender);
            event.start();
            return true;
        });

        events.put("meteor-shower", MeteorShower.class);

        valid_events.put(NaturalDisasters.namespace + ":acid_rain", (CommandSourceStack source) -> {
            CommandSender sender = source.getSender();
            sender.sendMessage("Starting Acid Rain.");
            AcidRain event = new AcidRain((Player) sender);
            event.start();
            return true;
        });

        events.put("acid-rain", AcidRain.class);

        BasicCommand startNaturalEvent = new StartNaturalEvent();
        registerCommand("startevent", startNaturalEvent);

        registerCommand("seteventduration", new SetDisasterLength());
        registerCommand("geteventduration", new GetDisasterLength());

        registerCommand("seteventproperty", new SetDisasterProperty());

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
