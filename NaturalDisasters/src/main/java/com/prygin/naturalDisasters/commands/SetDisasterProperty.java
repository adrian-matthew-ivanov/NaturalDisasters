package com.prygin.naturalDisasters.commands;

import com.prygin.naturalDisasters.events.NaturalDisaster;
import com.prygin.naturalDisasters.NaturalDisasters;
import com.prygin.naturalDisasters.helper.ReadPerDisasterConfig;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class SetDisasterProperty implements BasicCommand {
    public void execute(@NotNull CommandSourceStack source, String @NotNull [] args) {
        NaturalDisasters plugin = NaturalDisasters.getPlugin(NaturalDisasters.class);

        FileConfiguration config = plugin.getConfig();

        config.set(args[0] + "." + args[1], args[2]);

        Class<?> disaster = NaturalDisasters.events.get(args[0]);

        try {
            NaturalDisaster disasterInstance = (NaturalDisaster) disaster.getDeclaredConstructor().newInstance();
            Class<?> valueclass = disasterInstance.properties.get(args[1]).getClass();
            disasterInstance.properties.put(args[1], valueclass.cast(args[2]));
        } catch (Exception ignored) {

        }

        source.getSender().sendMessage("Set disaster property " + args[0] + ":" + args[1] + " -> " + args[2]);
    }
    @Override
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack source, String[] args) {
        if (args.length > 2) {
            return List.of("<value>");
        } else if (args.length > 1) {
            return Objects.requireNonNull(ReadPerDisasterConfig.getProperties(args[0])).getKeys(true);
        } else {
            return NaturalDisasters.events.keySet();
        }
    }
}
