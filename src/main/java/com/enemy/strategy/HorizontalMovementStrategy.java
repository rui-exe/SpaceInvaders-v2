package com.enemy.strategy;

import com.enemy.Enemy;

public class HorizontalMovementStrategy implements MovementStrategy {
    @Override
    public void move(Enemy enemy, boolean right) {
        if (right) {
            enemy.moveRight();
        } else enemy.moveLeft();
    }
}
