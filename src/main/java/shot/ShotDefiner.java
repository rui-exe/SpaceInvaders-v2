package shot;

import com.googlecode.lanterna.graphics.TextGraphics;
import position.Position;

import java.io.IOException;

public interface ShotDefiner {
    Integer getDamage();
    Integer getWidth();
    Integer getVelocity();
    Position getPosition();
    void moveDown();
    void moveUp();
    void draw(TextGraphics graphics) throws IOException;
    String getColor();
}