public class AllFalseEntityVisitor implements EntityVisitor<Boolean> {

    @Override
    public Boolean visit(Ore ore) {
        return false;
    }

    @Override
    public Boolean visit(BlackSmith smith) {
        return false;
    }

    @Override
    public Boolean visit(Vein vein) {
        return false;
    }

    @Override
    public Boolean visit(MinerNotFull miner) {
        return false;
    }

    @Override
    public Boolean visit(MinerFull miner) {
        return false;
    }

    @Override
    public Boolean visit(Obstacle obstacle) {
        return false;
    }

    @Override
    public Boolean visit(OreBlob oreBlob) {
        return false;
    }

    @Override
    public Boolean visit(Quake quake) {
        return false;
    }

}
