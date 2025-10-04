package com.prygin.naturalDisasters.events;

import com.prygin.naturalDisasters.NaturalDisasters;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class NaturalDisaster {
    private BukkitTask executeTask;
    private int timer;

    public Map<String, Object> properties = new HashMap<>();

    public void start() {
        initialize();
        this.executeTask = Bukkit.getScheduler().runTaskTimer(NaturalDisasters.getPlugin(NaturalDisasters.class), this::loop,
                0L, 1L);
        timer = 0;
    }

    public void initialize() {

    };

    public void execute() {

    };

    public void loop() {
        execute();
        timer++;
        if (this.timer >= NaturalDisasters.eventLength) {
            stopExecute();
        }
    }

    public void stopExecute() {
        this.executeTask.cancel();
        this.end();
    }

    public void end() {

    };
}
