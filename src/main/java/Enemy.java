import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Enemy implements EnemyDefiner {
    private Position position;
    private final MovementStrategy movementStrategy;
    private final ShootingStrategy shootingStrategy;
    private Integer health;
    List<Shot> shots;
    boolean movingRight;
    boolean movingDown;

    Enemy(Integer health, Position position, MovementStrategy movementStrategy, ShootingStrategy shootingStrategy) {
        this.position = position;
        this.health = health;
        this.movementStrategy = movementStrategy;
        this.shootingStrategy = shootingStrategy;
        this.shots = new ArrayList<Shot>();
        this.movingRight = true;
        this.movingDown = false;
    }

    @Override
    public MovementStrategy getMovementStrategy(){
        return movementStrategy;
    }

    @Override
    public ShootingStrategy getShootingStrategy(){
        return shootingStrategy;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }


    @Override
    public Integer getHealth() {
        return health;
    }

    @Override
    public void setHealth(Integer health) {
        this.health = health;
    }

    @Override
    public List<Shot> getShots() {
        return shots;
    }

    @Override
    public void move() {
        movementStrategy.move(this);
    }

    @Override
    public void moveLeft() {
        position.setX(position.getX() - 1);
    }

    @Override
    public void moveRight() {
        position.setX(position.getX() + 1);
    }

    @Override
    public void moveUp() {
        position.setY(position.getY() - 1);
    }

    @Override
    public void moveDown() {
        position.setY(position.getY() + 1);
    }

    @Override
    public boolean isMovingRight() {
        return movingRight;
    }

    @Override
    public void setMoveRight(boolean moveRight) {
        movingRight = moveRight;
    }

    @Override
    public boolean isMovingDown() {
        return movingDown;
    }

    @Override
    public void setMoveDown(boolean moveDown) {
        movingDown = moveDown;
    }

    @Override
    public boolean isDead() {
        return health<=0;
    }

    @Override
    public void normalShot() {
        Position aux = new Position(position.getX(),position.getY());
        aux.setY(aux.getY() + 1);
        shots.add(new Shot(100, 1, 1, aux));
    }

    @Override
    public void bigShot() {
        Position aux = new Position(position.getX(),position.getY());
        aux.setY(aux.getY() +1);
        shots.add(new Shot(100, 3, 1, aux));
    }

    @Override
    public void damageShot() {
        Position aux = new Position(position.getX(),position.getY());
        aux.setY(aux.getY() +1);
        shots.add(new Shot(300, 1, 1, aux));
    }

    @Override
    public void shoot() {
        shootingStrategy.shoot(this);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(getColor()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "R");
    }

    @Override
    public void setShots(List<Shot> shots) {
        this.shots = shots;
    }

    @Override
    public String getColor() {
        String result;
        if (Math.random() < 0.5) {
            result = "#FFFFFF";
        } else {
            result = "#FFFF00";
        }
        return result;
    }

    @Override
    public void removeShot(Shot enemyShot) {
        shots.remove(enemyShot);
    }
}
