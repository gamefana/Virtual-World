import processing.core.PImage;

import java.util.List;


public class BlackSmith extends SimpleEntity
{

    public BlackSmith(String id, Point position,
                  List<PImage> images)
    {

        super(id,position,images);

    }


    @Override
    public <R> R accept(EntityVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
