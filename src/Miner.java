import processing.core.PImage;

import java.util.List;

public abstract class Miner extends MovingEntity {
    private final int resourceLimit;

    public Miner(String id, Point position,
                 List<PImage> images,
                 int actionPeriod, int animationPeriod,
                 int resourceLimit) {
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
    }

    public Point nextPosition(WorldModel world,
                                          Point destPos){
        int horiz = Integer.signum(destPos.getX() - super.getPosition().getX());
        Point newPos = new Point(super.getPosition().getX() + horiz,
                super.getPosition().getY());

        if (horiz == 0 || world.isOccupied(newPos)) {
            int vert = Integer.signum(destPos.getY() - super.getPosition().getY());
            newPos = new Point(super.getPosition().getX(),
                    super.getPosition().getY() + vert);

            if (vert == 0 || world.isOccupied(newPos)) {
                newPos = super.getPosition();
            }
        }

        return newPos;
    }

    protected void transform(WorldModel world,
                                EventScheduler scheduler, ImageStore imageStore){
        Miner miner = createOppositeTypeMiner();
        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(miner);
        miner.scheduleActions(scheduler, world, imageStore);

    }

    protected abstract Miner createOppositeTypeMiner();

    public int getResourceLimit() {
        return resourceLimit;
    }
}
