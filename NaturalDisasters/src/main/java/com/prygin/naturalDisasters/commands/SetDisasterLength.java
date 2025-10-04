package com.prygin.naturalDisasters.commands;

import com.prygin.naturalDisasters.NaturalDisasters;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.NotNull;

public class SetDisasterLength implements BasicCommand {
    @Override
    public void execute(@NotNull CommandSourceStack source, String @NotNull [] args) {
        try {
            NaturalDisasters.eventLength = Integer.parseInt(args[0]);
            source.getSender().sendMessage("Set event duration to " + args[0] + " ticks.");
        } catch (Exception e) {
            source.getSender().sendMessage("Please enter an integer amount of time (ticks). ");
        }
    }
}
