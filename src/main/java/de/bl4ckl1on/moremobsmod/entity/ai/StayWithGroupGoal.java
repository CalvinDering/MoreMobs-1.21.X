package de.bl4ckl1on.moremobsmod.entity.ai;

import de.bl4ckl1on.moremobsmod.entity.custom.FlamingoEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;
import java.util.List;

public class StayWithGroupGoal<T extends Mob> extends Goal {
    private final T mob;
    private final Class<T> mobClass;
    private final double speed;
    private final double maxDistance;

    public StayWithGroupGoal(T mob, Class<T> mobClass, double speed, double maxDistance) {
        this.mob = mob;
        this.mobClass = mobClass;
        this.speed = speed;
        this.maxDistance = maxDistance;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        List<T> nearby = mob.level().getEntitiesOfClass(mobClass, mob.getBoundingBox().inflate(maxDistance));

        if(nearby.isEmpty()) {
            return false;
        }

        double avgX = 0;
        double avgY = 0;
        double avgZ = 0;

        for(T m : nearby) {
            avgX += m.getX();
            avgY += m.getY();
            avgZ += m.getZ();
        }

        avgX /= nearby.size();
        avgY /= nearby.size();
        avgZ /= nearby.size();

        double distance = mob.distanceToSqr(avgX, avgY, avgZ);
        return distance > maxDistance * maxDistance;
    }

    @Override
    public void tick() {
        List<T> nearby = mob.level().getEntitiesOfClass(mobClass, mob.getBoundingBox().inflate(maxDistance));

        if(nearby.isEmpty()) {
            return;
        }

        double avgX = 0;
        double avgY = 0;
        double avgZ = 0;

        for(T m : nearby) {
            avgX += m.getX();
            avgY += m.getY();
            avgZ += m.getZ();
        }

        avgX /= nearby.size();
        avgY /= nearby.size();
        avgZ /= nearby.size();

        mob.getNavigation().moveTo(avgX, avgY, avgZ, speed);
    }
}
