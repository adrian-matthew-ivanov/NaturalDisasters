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

    public StartNaturalEvent() {

    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        CommandSender sender = source.getSender();

        for (String arg:args) {
            if (NaturalDisasters.valid_events.containsKey(arg)) {
                NaturalDisasters.valid_events.get(arg).apply(source);
            } else {
                sender.sendMessage("§cPlease enter a valid event");
            }
        }
    }

    @Override
    public Collection<String> suggest(CommandSourceStack source, String[] args) {
        return NaturalDisasters.valid_events.keySet();
    }
}
