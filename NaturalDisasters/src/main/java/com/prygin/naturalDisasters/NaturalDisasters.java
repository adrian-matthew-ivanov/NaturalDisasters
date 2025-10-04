package com.prygin.naturalDisasters;

import com.prygin.naturalDisasters.commands.*;
import com.prygin.naturalDisasters.events.AcidRain;
import com.prygin.naturalDisasters.events.MeteorShower.MeteorShower;
import com.prygin.naturalDisasters.events.NaturalDisaster;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class NaturalDisasters extends JavaPlugin {

    public static String namespace = "naturaldisasters";

    public static int eventLength;

    public static Map<String, Function<CommandSourceStack, Boolean>> valid_events = new HashMap<>();

    public static Map<String, Class<?>> events = new HashMap<>();

    public static List<NaturalDisaster> current_events = List.of();

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

        registerCommand("stopallevents", new StopAllEvents());

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
