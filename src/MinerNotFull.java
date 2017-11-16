import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class MinerNotFull extends Miner {
    private int resourceCount;

    public MinerNotFull(String id,
                        Point position,
                        List<PImage> images,
                        int resourceLimit, int resourceCount,
                        int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod, resourceLimit);
        this.resourceCount = resourceCount;
    }

    public void executeActivity(
            WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> notFullTarget = world.findNearest(super.getPosition(),
                new AllFalseEntityVisitor() {
                    @Override
                    public Boolean visit(Ore ore) {
                        return true;
                    }
                });

        if (!notFullTarget.isPresent() ||
                !moveTo(world, notFullTarget.get(), scheduler) ||
                !isFull()) {
            scheduler.scheduleEvent(this,
                    createActivityAction(world, imageStore),
                    super.getActionPeriod());
        }
        else {
            transform(world, scheduler, imageStore);
        }
    }

    private boolean isFull(){
        return this.resourceCount >= super.getResourceLimit();
    }

    @Override
    protected boolean targetAdjacent(WorldModel world, Entity target, EventScheduler scheduler) {
        if (Point.adjacent(super.getPosition(), target.getPosition())) {
            this.resourceCount += 1;
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);

            return true;
        }
        return false;
    }

    @Override
    public <R> R accept(EntityVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    protected Miner createOppositeTypeMiner(){
        return EntityCreator.createMinerFull(super.getId(), super.getResourceLimit(),
                super.getPosition(), super.getActionPeriod(), super.getAnimationPeriod(),
                this.getImages());

    }


}
