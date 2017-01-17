package com.murano500k.test.beegame.model;

/**
 * Created by artem on 1/16/17.
 */
import static com.murano500k.test.beegame.GameLogic.*;

public class QueenBee extends Bee {

	public QueenBee(int index) {
		super(NAME_QUEEN, MAX_HP_QUEEN, MAX_COUNT_QUEEN, DAMAGE_PER_HIT_QUEEN, index);
	}
}
