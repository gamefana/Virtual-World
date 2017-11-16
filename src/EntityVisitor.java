interface EntityVisitor<R>
{
    R visit(Ore ore);
    R visit (BlackSmith smith);
    R visit (Vein vein);
    R visit (MinerNotFull miner);
    R visit (MinerFull miner);
    R visit (Obstacle obstacle);
    R visit (OreBlob oreBlob);
    R visit (Quake quake);
}