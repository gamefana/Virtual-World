public class Animation implements Action {
    private final AnimableEntity entity;
    private final int repeatCount;

    public Animation(AnimableEntity entity,
                     int repeatCount) {
        this.entity = entity;
        this.repeatCount = repeatCount;
    }

    public void executeAction(EventScheduler scheduler) {
                executeAnimationAction(scheduler);

    }

    private void executeAnimationAction(EventScheduler scheduler) {
        this.entity.nextImage();

        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity,
                    this.entity.createAnimationAction(
                            Math.max(this.repeatCount - 1, 0)),
                    this.entity.getAnimationPeriod());
        }
    }

}
