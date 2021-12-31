import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class SpellTPBack extends SpellTemplate{
    SpellTPBack(Position pos){
        this.pos = pos;
        this.symbol = 'T';
    }
    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "T");
    }

}