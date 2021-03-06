package com.arena;

import com.enemy.Enemy;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.spaceship.Spaceship;
import com.spell.template.SpellTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public interface ArenaDefiner {

    void setLevel(Integer level);

    List<Enemy> createEnemies();

    void addEnemy(Enemy e);

    void addSpell();

    void setSpaceship(Spaceship s);

    Integer getLevel();

    List<Enemy> getEnemies();

    List<SpellTemplate> getSpells();

    Spaceship getSpaceship();

    Integer getWidth();

    Integer getHeight();

    void draw(TextGraphics graphics) throws IOException;

    void checkCaughtSpell();

    void checkShotCollisions();

    void checkShotCollisionsHelper(int i,int j);

    void CollisionRemover(int i,int j,int k,List<Integer>shots);

    boolean checkShotHitEnemiesHelper(Integer i, Integer j, Integer shotLeft, Integer shotRight);

    void checkShotHitEnemiesHelperV2(Integer j);

    void checkShotsHitSpaceship();

    void checkShotsHitSpaceshipHelper(Integer i, Integer j,Integer shotLeft, Integer shotRight);

    void processKey(KeyStroke key);

    void checkShotsHitEnemies();

    void removeShotsOutOfBounds();

    void removeShotsOutOfBoundsHelper();

    void moveEnemies();

    void moveShots();


    void updateSpaceShipState();

    void checkActiveSpells();


    void moveEnemiesLeft();

    void moveEnemiesRight();

    void createSpell(Random random);

    void shootEnemies(Random random);
}
