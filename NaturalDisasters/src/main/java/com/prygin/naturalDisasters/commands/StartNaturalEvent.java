package com.prygin.naturalDisasters.commands;

import com.prygin.naturalDisasters.NaturalDisasters;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;

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
