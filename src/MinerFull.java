import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class MinerFull extends Miner {


    public MinerFull(String id,
                     Point position,
                     List<PImage> images, int resourceLimit, int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod, resourceLimit);
    }

    public void executeActivity(WorldModel world,
                                ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fullTarget = world.findNearest(super.getPosition(), new AllFalseEntityVisitor(){
            @Override
            public Boolean visit(BlackSmith smith){ return true;}
        } );

        if (fullTarget.isPresent() &&
                moveTo(world, fullTarget.get(), scheduler)) {
            transform(world, scheduler, imageStore);
        } else {
            scheduler.scheduleEvent(this,
                    createActivityAction(world, imageStore),
                    super.getActionPeriod());
        }
    }

    @Override
    protected boolean targetAdjacent(WorldModel world, Entity target, EventScheduler scheduler) {
        return Point.adjacent(super.getPosition(), target.getPosition());
    }


    @Override
    public <R> R accept(EntityVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    protected Miner createOppositeTypeMiner(){
        return EntityCreator.createMinerNotFull(super.getId(), super.getResourceLimit(),
                super.getPosition(), super.getActionPeriod(), super.getAnimationPeriod(),
                this.getImages());

    }

}

