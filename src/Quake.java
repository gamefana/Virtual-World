import processing.core.PImage;

import java.util.List;

public class Quake extends AnimateEntity {
    private int QUAKE_ANIMATION_REPEAT_COUNT = 10;


    public Quake(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    @Override
    protected void scheduleAnimationAction(EventScheduler scheduler) {
        scheduler.scheduleEvent(this,
                createAnimationAction(QUAKE_ANIMATION_REPEAT_COUNT), super.getAnimationPeriod());
    }


    public void executeActivity(WorldModel world, ImageStore imageStore,
                                      EventScheduler scheduler) {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }

    @Override
    public <R> R accept(EntityVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
