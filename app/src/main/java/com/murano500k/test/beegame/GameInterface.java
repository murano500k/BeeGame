package com.murano500k.test.beegame;

import com.murano500k.test.beegame.model.Bee;

import java.util.ArrayList;

/**
 * Created by artem on 1/16/17.
 */

public interface GameInterface {
	void spawnBees(ArrayList<Bee> bees);

	void beeIsHit(Bee bee);
	void gameOver();


}
