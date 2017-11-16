import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public abstract class MovingEntity extends AnimateEntity implements Movable {
    public MovingEntity(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    public boolean moveTo(WorldModel world,
                             Entity target, EventScheduler scheduler){
        if (targetAdjacent(world,target,scheduler))
            return true;

        else {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!super.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    @Override
    protected void scheduleAnimationAction(EventScheduler scheduler) {
        scheduler.scheduleEvent(this,
                createAnimationAction(0), super.getAnimationPeriod());
    }

    protected abstract boolean targetAdjacent(WorldModel world,
                                              Entity target, EventScheduler scheduler);
}
