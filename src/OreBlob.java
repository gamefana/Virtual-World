import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class OreBlob extends MovingEntity {


    private final String QUAKE_KEY = "quake";

    public OreBlob(String id,
                   Point position,
                   List<PImage> images,
                   int actionPeriod,
                   int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
    }



    public void executeActivity(WorldModel world,
                                ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> blobTarget = world.findNearest(super.getPosition(), new AllFalseEntityVisitor(){
            @Override
            public Boolean visit(Vein vein){
                return true;
            }
        });
        long nextPeriod = super.getActionPeriod();

        if (blobTarget.isPresent()) {
            Point tgtPos = blobTarget.get().getPosition();

            if (moveTo(world, blobTarget.get(), scheduler)) {
                Quake quake = EntityCreator.createQuake(tgtPos, imageStore.getImageList(QUAKE_KEY));

                world.addEntity(quake);
                nextPeriod += super.getActionPeriod();
                quake.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                createActivityAction(world, imageStore),
                nextPeriod);
    }

    @Override
    protected boolean targetAdjacent(WorldModel world, Entity target, EventScheduler scheduler) {
        if (Point.adjacent(super.getPosition(), target.getPosition())) {
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            return true;
        }
        return false;
    }

    public Point nextPosition(WorldModel world,
                              Point destPos) {
        EntityVisitor<Boolean> oreChecker = new AllFalseEntityVisitor() {
            @Override
            public Boolean visit(Ore ore) {
                return true;
            }
        };
        int horiz = Integer.signum(destPos.getX() - super.getPosition().getX());
        Point newPos = new Point(super.getPosition().getX() + horiz,
                super.getPosition().getY());

        Optional<Entity> occupant = world.getOccupant(newPos);

        if (horiz == 0 ||
                (occupant.isPresent() && !(occupant.get().accept(oreChecker))));

            int vert = Integer.signum(destPos.getY() - super.getPosition().getY());
            newPos = new Point(super.getPosition().getX(),
                    super.getPosition().getY() + vert);
            occupant = world.getOccupant(newPos);

            if (vert == 0 ||
                    (occupant.isPresent() && !(occupant.get().accept(oreChecker)))) {
                newPos = super.getPosition();
            }
        return newPos;
        }




    @Override
    public <R> R accept(EntityVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
