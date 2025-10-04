package com.prygin.naturalDisasters.MeteorShower;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.BlockDisplay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Meteor {
    public boolean landed;
    private float speed;
    float x;
    float y;
    float z;
    World world;

    List<BlockDisplay> blockDisplays;

    List<Material> validBlocks = List.of(
            Material.NETHERRACK,
            Material.OBSIDIAN,
            Material.CRYING_OBSIDIAN,
            Material.MAGMA_BLOCK,
            Material.ORANGE_STAINED_GLASS,
            Material.COBBLED_DEEPSLATE
    );

    public Meteor(float x, float y, float z, World world) {
        // Set x y and z
        this.x = x;
        this.y = y;
        this.z = z;

        this.world = world;

        if (world.getBlockAt((int)x, (int)y, (int)z).getType() != Material.AIR) {
            return;
        }

        Random random = new Random();

        this.blockDisplays = new ArrayList<>();

        // Generate block displays
        for (int i = 0; i < 20; i++) {
            BlockDisplay display = world.spawn(new Location(world, x+random.nextFloat()*2, y+random.nextFloat()*2, z+random.nextFloat()*2), BlockDisplay.class, entity -> {
                entity.setBlock(validBlocks.get(random.nextInt(validBlocks.size())).createBlockData());
            });

            this.blockDisplays.add(display);
        }

        // Get it all moving

        this.landed = false;

        this.speed = random.nextInt(1,3);
    }

    public void update() {
        this.speed += 0.1F;
        for (BlockDisplay blockDisplay:this.blockDisplays) {
            if (blockDisplay == null || blockDisplay.isDead()) return;

            Location loc = blockDisplay.getLocation();

            loc.subtract(0, this.speed, 0);
            blockDisplay.teleport(loc);

            if (this.landed) {
                blockDisplay.remove();
            }

            if (loc.getBlock().getType().isSolid()) {
                blockDisplay.getWorld().createExplosion(blockDisplay, 5);
                blockDisplay.remove();
                this.landed = true;
            }
        }

    }

    public void kill() {
        Iterator<BlockDisplay> iterator = this.blockDisplays.iterator();
        while (iterator.hasNext()) {
            BlockDisplay blockDisplay = iterator.next();
            iterator.remove();
            blockDisplay.remove();
        }
    }
}
