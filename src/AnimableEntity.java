public interface AnimableEntity extends Entity {
    int getAnimationPeriod();
    void nextImage();
    Action createAnimationAction(int repeatCount);

}
