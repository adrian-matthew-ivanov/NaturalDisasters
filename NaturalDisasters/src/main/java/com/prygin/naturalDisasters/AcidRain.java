package com.prygin.naturalDisasters;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class AcidRain extends NaturalDisaster{

    private Player player;
    private World world;

    private int damageTimer;

    public static final String name = "acid-rain";

    public AcidRain(Player player) {
        this.world = player.getWorld();
        this.player = player;
        this.damageTimer = 0;
        this.properties.put("damage-gap", Integer.parseInt((String) ReadPerDisasterConfig.get_property("damage-gap", name)));
    }

    @Override
    public void initialize() {
        this.player.showTitle(Title.title(Component.text("Acid Rain"),
                Component.text("Seek Shelter")));

        this.world.setStorm(true);
    }

    @Override
    public void execute() {
        this.damageTimer++;

        if (this.damageTimer > (int) properties.get("damage-gap")) {
            this.damageTimer = 0;
            for (int i = 0; i < 320-this.player.getY(); i++) {
                if (this.world.getBlockAt(new Location(this.world, this.player.getX(),
                        this.player.getY()+i, this.player.getZ())).getType() != Material.AIR) {
                    return;
                }
            }
            this.player.damage(3);
        }
    }

    @Override
    public void end() {
        this.world.setStorm(false);
    }
}
