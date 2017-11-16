import processing.core.PImage;

import java.util.List;

public abstract class ActiveEntity extends SimpleEntity implements ActivableEntity {
    private final int actionPeriod;


    public ActiveEntity(String id, Point position, List<PImage> images, int actionPeriod) {
        super(id, position, images);
        this.actionPeriod = actionPeriod;
    }


    public void scheduleActions(EventScheduler scheduler,
                                WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this,
                createActivityAction(world, imageStore),
                this.actionPeriod);

    }

    public Action createActivityAction(WorldModel world,
                                       ImageStore imageStore) {
        return new Activity( this, world, imageStore, 0);
    }

    public int getActionPeriod() {
        return actionPeriod;
    }


}
