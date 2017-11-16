public interface ActivableEntity extends Entity {
    void scheduleActions(EventScheduler scheduler,
                         WorldModel world, ImageStore imageStore);

    Action createActivityAction(WorldModel world,
                                ImageStore imageStore);
    void executeActivity(WorldModel world,
                         ImageStore imageStore, EventScheduler scheduler);

}
