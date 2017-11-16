public class Activity implements Action{
    private final ActivableEntity entity;
    private final WorldModel world;
    private final ImageStore imageStore;
    public Activity(ActivableEntity entity, WorldModel world,
                    ImageStore imageStore, int repeatCount) {

        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
    }

    public void executeAction(EventScheduler scheduler) {
        executeActivityAction(scheduler);
    }

    private void executeActivityAction(EventScheduler scheduler) {
        this.entity.executeActivity(this.world,
                    this.imageStore, scheduler);
    }

}
