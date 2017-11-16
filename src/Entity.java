import processing.core.PImage;

import java.util.List;

public interface Entity {
    List<PImage> getImages();
    int getImageIndex();
    Point getPosition();
    void setPosition(Point position);
    public PImage getCurrentImage();
    public abstract <R> R accept(EntityVisitor<R> visitor);

}
