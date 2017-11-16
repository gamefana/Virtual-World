import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class Vein extends ActiveEntity {

    private final String ORE_ID_PREFIX = "ore -- ";
    private final int ORE_CORRUPT_MIN = 20000;
    private final int ORE_CORRUPT_MAX = 30000;
    private final String ORE_KEY = "ore";

    public Vein(String id, Point position, List<PImage> images, int actionPeriod) {
        super(id, position, images, actionPeriod);
    }

    public void executeActivity(WorldModel world,
                                ImageStore imageStore, EventScheduler scheduler) {
        Optional<Point> openPt = world.findOpenAround(super.getPosition());

        if (openPt.isPresent()) {
            ActivableEntity ore = EntityCreator.createOre(ORE_ID_PREFIX + super.getId(),
                    openPt.get(), ORE_CORRUPT_MIN +
                            Functions.getRand().nextInt(ORE_CORRUPT_MAX - ORE_CORRUPT_MIN),
                    imageStore.getImageList(ORE_KEY));
            world.addEntity(ore);
            ore.scheduleActions(scheduler, world, imageStore);
        }

        scheduler.scheduleEvent(this,
                createActivityAction(world, imageStore),
                super.getActionPeriod());
    }

    @Override
    public <R> R accept(EntityVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
