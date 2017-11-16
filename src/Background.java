import java.util.List;

import processing.core.PImage;

final class Background {
    private final String id;
    private final List<PImage> images;

    public List<PImage> getImages() {
        return images;
    }

    public int getImageIndex() {
        return 0;
    }


    public Background(String id, List<PImage> images) {
        this.id = id;
        this.images = images;
    }
    public PImage getCurrentImage() {

            return this.getImages()
                    .get(getImageIndex());

    }
}
