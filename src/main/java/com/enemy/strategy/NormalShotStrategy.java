package com.enemy.strategy;

import com.enemy.Enemy;

public class NormalShotStrategy implements ShootingStrategy {
    @Override
    public void shoot(Enemy enemy) {
        enemy.normalShot();
    }
}
