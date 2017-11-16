import processing.core.PImage;

import java.util.List;

public abstract class SimpleEntity implements Entity {
    private final String id;
    private Point position;
    private final List<PImage> images;
    private int imageIndex;

    public SimpleEntity(String id, Point position,
                        List<PImage> images)
    {

        this.imageIndex = 0;
        this.id = id;
        this.position = position;
        this.images = images;

    }
    public PImage getCurrentImage() {

            return this.images.get(getImageIndex());

    }

    public String getId() {
        return id;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public List<PImage> getImages() {
        return images;
    }

    protected void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    @Override
    public int getImageIndex() {
        return imageIndex;
    }


    @Override
    public Point getPosition() {
        return position;
    }


}
