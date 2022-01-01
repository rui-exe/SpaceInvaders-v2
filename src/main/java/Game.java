import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static java.lang.System.exit;

public class Game implements GameDefiner{
    Screen screen;
    private Arena arena;
    public Game(){
        arena = new Arena(150,50);
        try {
            TerminalSize terminalSize = new TerminalSize(arena.getWidth(), arena.getHeight());
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw() throws IOException {
        this.screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        arena.draw(graphics);
        this.screen.refresh();
    }

    @Override
    public void run() throws IOException {
        InputThread myThread = new InputThread();
        myThread.screen = this.screen;
        myThread.start();
        while (true) {
            if (arena.getEnemies().size() == 0) arena.createEnemies();
            draw();
            if (arena.getSpaceship().isDead()) {
                screen.close();
                System.out.println("Game Over");
                break;
            }
            KeyStroke key = myThread.key;
            try {
                myThread.sleep(15);
            } catch (InterruptedException f) {
                f.printStackTrace();
            }
            if (key != null) {
                if (key.getKeyType() == KeyType.EOF) {
                    screen.close();
                    exit(0);
                }
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    screen.close();
                    exit(0);
                }
            }
            arena.moveShots();
            if (key != null){
                arena.processKey(key);
                myThread.key = null;
            }
            arena.moveEnemies();
            arena.shootEnemies();
            arena.checkShotCollisions();
            arena.checkShotsHitEnemies();
            arena.checkShotsHitSpaceship();
            arena.checkActiveSpells();
            arena.createSpell();
            arena.checkCaughtSpell();
            arena.updateSpaceShipState();
            try {
                myThread.sleep(15);
            } catch (InterruptedException f) {
                f.printStackTrace();
            }
        }
    }
}