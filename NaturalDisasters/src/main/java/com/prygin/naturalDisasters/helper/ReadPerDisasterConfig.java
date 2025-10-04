package com.prygin.naturalDisasters.helper;

import com.prygin.naturalDisasters.NaturalDisasters;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Nullable;

public class ReadPerDisasterConfig {
    private static final FileConfiguration config = NaturalDisasters.getPlugin(NaturalDisasters.class).getConfig();
    public static Object get_property(String name, String disasterName) {
        return config.get(disasterName + "." + name);
    }

    public static @Nullable ConfigurationSection getProperties(String disaster) {
        return config.getConfigurationSection(disaster);
    }
}
