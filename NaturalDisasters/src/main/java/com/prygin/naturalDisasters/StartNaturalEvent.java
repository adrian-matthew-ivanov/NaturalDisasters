package com.prygin.naturalDisasters;

import com.prygin.naturalDisasters.MeteorShower.MeteorShower;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@NullMarked
public class StartNaturalEvent implements BasicCommand {
    public static Map<String, Function<CommandSourceStack, Boolean>> valid_events = new HashMap<>();

    public StartNaturalEvent() {
        valid_events.put(NaturalDisasters.namespace + ":meteor_shower", (CommandSourceStack source) -> {
            CommandSender sender = source.getSender();
            sender.sendMessage("Summoning a meteor shower.");
            MeteorShower event = new MeteorShower((Player) sender);
            event.start();
            return true;
        });

        valid_events.put(NaturalDisasters.namespace + ":acid_rain", (CommandSourceStack source) -> {
            CommandSender sender = source.getSender();
            sender.sendMessage("Starting Acid Rain.");
            AcidRain event = new AcidRain((Player) sender);
            event.start();
            return true;
        });
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        CommandSender sender = source.getSender();

        for (String arg:args) {
            if (valid_events.containsKey(arg)) {
                valid_events.get(arg).apply(source);
            } else {
                sender.sendMessage("§cPlease enter a valid event");
            }
        }
    }

    @Override
    public Collection<String> suggest(CommandSourceStack source, String[] args) {
        return valid_events.keySet();
    }
}
