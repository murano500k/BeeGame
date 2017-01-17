package com.murano500k.test.beegame.model;

import static com.murano500k.test.beegame.GameLogic.DAMAGE_PER_HIT_WORKER;
import static com.murano500k.test.beegame.GameLogic.MAX_COUNT_WORKER;
import static com.murano500k.test.beegame.GameLogic.MAX_HP_WORKER;
import static com.murano500k.test.beegame.GameLogic.NAME_WORKER;

/**
 * Created by artem on 1/16/17.
 */

public class WorkerBee extends Bee {

	public WorkerBee(int index) {
		super(NAME_WORKER, MAX_HP_WORKER , MAX_COUNT_WORKER, DAMAGE_PER_HIT_WORKER, index);
	}

}
