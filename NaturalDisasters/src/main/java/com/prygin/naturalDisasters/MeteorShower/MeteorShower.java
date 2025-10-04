package com.prygin.naturalDisasters.MeteorShower;

import com.prygin.naturalDisasters.NaturalDisaster;
import com.prygin.naturalDisasters.NaturalDisasters;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MeteorShower extends NaturalDisaster {
    private final Random random;
    List<Meteor> meteors;
    World world;
    Player player;

    boolean over;

    int timer;

    public MeteorShower(Player player) {
        this.world = player.getWorld();
        this.player = player;

        this.timer = 0;

        this.random = new Random();

        this.meteors = new ArrayList<>();

        this.over = false;
    }

    @Override
    public void initialize() {
        final Title title = Title.title(Component.text("§cMeteor Shower"), Component.text("§cSeek Shelter"));

        player.showTitle(title);
    }

    @Override
    public void execute() {
        Iterator<Meteor> iterator = this.meteors.iterator();
        while (iterator.hasNext()) {
            Meteor meteor = iterator.next();
            meteor.update();
            if (meteor.landed) {
                meteor.kill();
                iterator.remove();
            }
        }

        meteors.add(new Meteor(random.nextFloat((float) (player.getLocation().getX()-50), (float) (player.getLocation().getX()+50)),
                random.nextFloat((float) (player.getLocation().getY() + 20), (float) (player.getLocation().getY() + 100)),
                random.nextFloat((float) (player.getLocation().getZ()-50), (float) (player.getLocation().getZ()+50)), world));
    }

    @Override
    public void end() {
        final Title title = Title.title(Component.text("Meteor Shower Over"), Component.text(""));

        player.showTitle(title);
    }
}
