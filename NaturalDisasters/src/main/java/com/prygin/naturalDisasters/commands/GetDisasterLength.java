package com.prygin.naturalDisasters.commands;

import com.prygin.naturalDisasters.NaturalDisasters;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.NotNull;

public class GetDisasterLength implements BasicCommand {
    @Override
    public void execute(@NotNull CommandSourceStack source, String @NotNull [] args) {
        source.getSender().sendMessage("The natural event duration is currently " + NaturalDisasters.eventLength + " ticks.");
    }
}