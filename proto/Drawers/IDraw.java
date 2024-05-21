package proto.Drawers;

import java.awt.*;
import java.awt.image.ImageObserver;

public interface IDraw {
    public void draw(Graphics2D renderer, int x, int y, ImageObserver imageObserver);
}
