package com.prygin.naturalDisasters.commands;

import com.prygin.naturalDisasters.NaturalDisasters;
import com.prygin.naturalDisasters.events.NaturalDisaster;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;

public class StopAllEvents implements BasicCommand {

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] strings) {
        for (NaturalDisaster event:NaturalDisasters.current_events) {
            event.stopExecute();
            NaturalDisasters.current_events.remove(event);
        }

    }
}
