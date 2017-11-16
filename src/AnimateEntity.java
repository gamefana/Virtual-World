import processing.core.PImage;

import java.util.List;

public abstract class AnimateEntity extends ActiveEntity implements AnimableEntity {
    private final int animationPeriod;

    public AnimateEntity(String id, Point position,
                         List<PImage> images,
                         int actionPeriod,
                         int animationPeriod) {
        super(id, position, images, actionPeriod);
        this.animationPeriod = animationPeriod;
    }


    public int getAnimationPeriod() {
        return this.animationPeriod;

    }

    public void scheduleActions(EventScheduler scheduler,
                                WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this,
                createActivityAction(world, imageStore),
                super.getActionPeriod());
        scheduleAnimationAction(scheduler);

    }

    protected abstract void scheduleAnimationAction(EventScheduler scheduler);
    public void nextImage() {
        super.setImageIndex((super.getImageIndex() + 1) % super.getImages().size());
    }


    public Action createAnimationAction(int repeatCount) {
        return new Animation( this,  repeatCount);
    }


}
