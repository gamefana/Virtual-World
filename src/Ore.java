import processing.core.PImage;

import java.util.List;

public class Ore extends ActiveEntity {

    private final String BLOB_KEY = "blob";
    private final String BLOB_ID_SUFFIX = " -- blob";
    private final int BLOB_PERIOD_SCALE = 4;
    private final int BLOB_ANIMATION_MIN = 50;
    private final int BLOB_ANIMATION_MAX = 150;


    public Ore(String id, Point position, List<PImage> images, int actionPeriod) {
        super(id, position, images, actionPeriod);
    }

    public void executeActivity(WorldModel world,
                                ImageStore imageStore, EventScheduler scheduler) {
        Point pos = this.getPosition();  // store current position before removing

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        OreBlob blob = EntityCreator.createOreBlob(super.getId() + BLOB_ID_SUFFIX,
                pos, super.getActionPeriod() / BLOB_PERIOD_SCALE,
                BLOB_ANIMATION_MIN +
                        Functions.getRand().nextInt(BLOB_ANIMATION_MAX - BLOB_ANIMATION_MIN),
                imageStore.getImageList(BLOB_KEY));

        world.addEntity(blob);
        blob.scheduleActions(scheduler, world, imageStore);
    }

    @Override
    public <R> R accept(EntityVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
